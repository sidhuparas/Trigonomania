package com.parassidhu.trigonomania;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MathAdapter extends RecyclerView.Adapter<MathAdapter.ViewHolder> {

    private double[] trigValues;
    private boolean theta;

    MathAdapter(double[] trigValues, boolean theta) {
        this.trigValues = trigValues;
        this.theta = theta;
    }

    @NonNull
    @Override
    public MathAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.row_layout, viewGroup, false);
        return new MathAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MathAdapter.ViewHolder viewHolder, int i) {
        viewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return trigValues.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.trig_func) TextView trigFunc;
        @BindView(R.id.trig_value) TextView trigValue;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            if (trigValues.length == 6) {
                trigFunc.setText(MathUtils.trigonometricFunctions[position]);
            } else {
                if (theta)
                    trigFunc.setText(MathUtils.sidesPlaceHolderTheta[position]);
                else
                    trigFunc.setText(MathUtils.sidesPlaceHolderPhi[position]);
            }

            for (int i = 0; i < trigValues.length; i++) {
                trigValues[i] = Math.round(trigValues[i] * 100) / 100.0;
            }

            trigValue.setText(String.valueOf(trigValues[position]));
        }
    }
}
