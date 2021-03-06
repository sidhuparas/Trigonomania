package com.parassidhu.trigonomania.adapters;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.parassidhu.trigonomania.MathUtils;
import com.parassidhu.trigonomania.R;
import com.parassidhu.trigonomania.model.FirstMethodModel;
import com.parassidhu.trigonomania.model.SecondMethodModel;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalculationsAdapter extends RecyclerView.Adapter<CalculationsAdapter.ViewHolder> {

    private List<FirstMethodModel> firstList;
    private List<SecondMethodModel> secondList;
    private final int itemType;

    public CalculationsAdapter(List list, int itemType) {
        this.itemType = itemType;
        Collections.reverse(list);
        if (itemType == 0)
            this.firstList = list;
        else
            this.secondList = list;
    }

    @NonNull
    @Override
    public CalculationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.recent_layout, viewGroup, false);
        return new CalculationsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalculationsAdapter.ViewHolder viewHolder, int i) {
        Log.d("ItemType", "onBindViewHolder: " + viewHolder.getItemViewType());
        viewHolder.bind(i, itemType);
    }

    @Override
    public int getItemCount() {
        if (itemType == 0)
            return firstList.size();
        else
            return secondList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.angle) TextView angle;
        @BindView(R.id.side) TextView side;
        @BindView(R.id.data) TextView data;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private String getAngleSign(int i) {
            if (i == 0)
                return "\u03C6";
            else
                return "\u03B8";
        }

        @SuppressLint("SetTextI18n")
        void bind(int position, int itemType) {
            if (itemType == 0) { // First Method
                FirstMethodModel item = firstList.get(position);
                angle.setText("Angle (" + getAngleSign(item.getAngle()) + "): " + item.getValueOfAngle());
                side.setText("Side (" + MathUtils.assignSide(item.getSide()) + "): " + item.getValueOfSide());

                String dataStr = item.getData();
                dataStr = makeArray(dataStr, item.getAngle() == 0 ?
                        MathUtils.sidesPlaceHolderPhi : MathUtils.sidesPlaceHolderTheta);

                data.setText(dataStr);

            } else { // Second Method
                SecondMethodModel item = secondList.get(position);
                angle.setText("Side (" + MathUtils.assignSide(item.getSide1()) + "): " + item.getValueOfSide1());
                side.setText("Side (" + MathUtils.assignSide(item.getSide2()) + "): " + item.getValueOfSide2());

                String dataStr = item.getData();
                dataStr = makeArray(dataStr, MathUtils.trigonometricFunctions);

                data.setText(dataStr);
            }
        }

        @NonNull
        private String makeArray(String dataStr, String[] array) {
            Gson gson = new Gson();
            String[] dataArray = gson.fromJson(dataStr, String[].class);
            String[] resultArray = new String[dataArray.length];
            dataStr = "";
            for (int i = 0; i < dataArray.length; i++) {
                resultArray[i] = array[i] + Math.round(Double.valueOf(dataArray[i])*100)/100.0;
                dataStr = dataStr.concat(resultArray[i]).concat("\n");
            }
            return dataStr;
        }
    }
}
