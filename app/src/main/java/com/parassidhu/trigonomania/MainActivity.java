package com.parassidhu.trigonomania;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import lib.kingja.switchbutton.SwitchMultiButton;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.resultList) RecyclerView resultList;
    @BindView(R.id.submit_btn) Button submitBtn;
    @BindView(R.id.switch_method) SwitchMultiButton switchMethod;

    @BindView(R.id.first_method) ConstraintLayout firstMethod;
    @BindView(R.id.second_method) ConstraintLayout secondMethod;

    @BindView(R.id.angle_et) MaterialEditText angleEditText;
    @BindView(R.id.side_et) MaterialEditText sideEditText;
    @BindView(R.id.side1_et) MaterialEditText side1EditText;
    @BindView(R.id.side2_et) MaterialEditText side2EditText;

    @BindView(R.id.switch_angle) SwitchMultiButton switchAngle;
    @BindView(R.id.switch_side) SwitchMultiButton switchSide;
    @BindView(R.id.switch_side1) SwitchMultiButton switchSide1;
    @BindView(R.id.switch_side2) SwitchMultiButton switchSide2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initListeners();

        firstMethod.setVisibility(View.VISIBLE);
        secondMethod.setVisibility(View.GONE);
    }

    private void initListeners() {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (switchMethod.getSelectedTab() == 0)
                        handleFirstMethod();
                    else
                        handleSecondMethod();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Please fill correct values!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        switchMethod.setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {
                if (position == 0) {
                    firstMethod.setVisibility(View.VISIBLE);
                    secondMethod.setVisibility(View.GONE);
                } else {
                    firstMethod.setVisibility(View.GONE);
                    secondMethod.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void handleFirstMethod() {
        int angleSwitch = switchAngle.getSelectedTab();
        int sideSwitch = switchSide.getSelectedTab();

        String angle, side;

        if (angleSwitch == 0)
            angle = "Phi";
        else
            angle = "Theta";

        if (sideSwitch == 0)
            side = "AB";
        else if (sideSwitch == 1)
            side = "AC";
        else
            side = "BC";

        double valueOfAngle = Double.valueOf(angleEditText.getText().toString());
        double valueOfSide = Double.valueOf(sideEditText.getText().toString());

        performCalculationsForFirstMethod(angle, side, valueOfAngle, valueOfSide);

        //setupResult(MathUtils.getTrigonometricValues(valueOfAngle, valueOfSide));
    }

    private void performCalculationsForFirstMethod(String angle, String side,
                                                   double valueOfAngle, double valueOfSide) {
        if (angle.equals("Theta"))
            setupResult(MathUtils.performCalculationsForTheta(side, valueOfAngle, valueOfSide));
        else
            setupResult(MathUtils.performCalculationsForPhi(side, valueOfAngle, valueOfSide));
    }

    private void handleSecondMethod() {

    }


    private void setupResult(double[] trigValues) {
        resultList.setLayoutManager(new GridLayoutManager(this, 2));

        MathAdapter adapter = new MathAdapter(trigValues);

        resultList.setAdapter(adapter);
    }


}
