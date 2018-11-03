package com.parassidhu.trigonomania;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MathAdapter extends RecyclerView.Adapter<MathAdapter.ViewHolder> {

    private String[] trigonometricFunctions = {
            "Sin(\u03B8)",
            "Cos(\u03B8)",
            "Tan(\u03B8)",
            "Cosec(\u03B8)",
            "Sec(\u03B8)",
            "Cot(\u03B8)"
    };

    private float[] trigValues;

    MathAdapter(float[] trigValues) {
        this.trigValues = trigValues;
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
        return trigonometricFunctions.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.trig_func) TextView trigFunc;
        @BindView(R.id.trig_value) TextView trigValue;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            trigFunc.setText(trigonometricFunctions[position]);
            trigValue.setText(String.valueOf(trigValues[position]));
        }
    }
}
