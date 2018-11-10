package com.parassidhu.trigonomania;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
                    else {
                        if (switchSide1.getSelectedTab() != switchSide2.getSelectedTab())
                            handleSecondMethod();
                        else
                            Toast.makeText(MainActivity.this, "Please enter values of two distinct sides.", Toast.LENGTH_SHORT).show();
                    }
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

        side = assignSide(sideSwitch);

        double valueOfAngle = Double.valueOf(angleEditText.getText().toString());
        double valueOfSide = Double.valueOf(sideEditText.getText().toString());

        performCalculationsForFirstMethod(angle, side, valueOfAngle, valueOfSide);
    }

    private void performCalculationsForFirstMethod(String angle, String side,
                                                   double valueOfAngle, double valueOfSide) {
        if (angle.equals("Theta"))
            setupResult(MathUtils.performCalculationsForTheta(side, valueOfAngle, valueOfSide));
        else
            setupResult(MathUtils.performCalculationsForPhi(side, valueOfAngle, valueOfSide));
    }

    private void handleSecondMethod() {
        int side1Switch = switchSide1.getSelectedTab();
        int side2Switch = switchSide2.getSelectedTab();

        String side1, side2;

        side1 = assignSide(side1Switch);
        side2 = assignSide(side2Switch);

        double valueOfSide1 = Double.valueOf(side1EditText.getText().toString());
        double valueOfSide2 = Double.valueOf(side2EditText.getText().toString());
        
        performCalculationsForSecondMethod(side1, side2, valueOfSide1, valueOfSide2);
    }

    private void performCalculationsForSecondMethod(String side1, String side2,
                                                    double valueOfSide1, double valueOfSide2) {
        setupResult(MathUtils.trigonometricCalculations(side1, side2, valueOfSide1, valueOfSide2));
    }

    private String assignSide(int sideSwitch){
        if (sideSwitch == 0)
            return "AB";
        else if (sideSwitch == 1)
            return "AC";
        else
            return "BC";
    }

    private void setupResult(double[] trigValues) {
        int columns = 2;

        if (trigValues.length == 3)
            columns = 1;

        resultList.setLayoutManager(new GridLayoutManager(this, columns));

        int angleSwitch = switchAngle.getSelectedTab();
        boolean theta = angleSwitch != 0;

        MathAdapter adapter = new MathAdapter(trigValues, theta);

        resultList.setAdapter(adapter);
    }
}
