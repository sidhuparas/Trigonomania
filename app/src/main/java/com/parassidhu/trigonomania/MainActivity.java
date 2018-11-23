package com.parassidhu.trigonomania;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.parassidhu.trigonomania.adapters.MathAdapter;
import com.parassidhu.trigonomania.model.FirstMethodModel;
import com.parassidhu.trigonomania.model.SecondMethodModel;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import lib.kingja.switchbutton.SwitchMultiButton;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.resultList) RecyclerView resultList;
    @BindView(R.id.submit_btn) Button submitBtn;
    @BindView(R.id.switch_method) SwitchMultiButton switchMethod;
    @BindView(R.id.adView) AdView adView;

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

    private MainViewModel mViewModel;
    private FirebaseAnalytics mAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initListeners();
        initAds();
        setupViewModel();

        firstMethod.setVisibility(View.VISIBLE);
        secondMethod.setVisibility(View.GONE);
    }

    private void init() {
        ButterKnife.bind(this);
        Stetho.initializeWithDefaults(this);
        mAnalytics = FirebaseAnalytics.getInstance(this);
        MobileAds.initialize(this, BuildConfig.APP_ID);
    }

    private void setupViewModel() {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    private void initListeners() {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "text");

                try {
                    if (switchMethod.getSelectedTab() == 0) {
                        handleFirstMethod();
                        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "FirstMethod");
                        mAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                    } else {
                        if (switchSide1.getSelectedTab() != switchSide2.getSelectedTab()) {
                            handleSecondMethod();
                            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "SecondMethod");
                        } else
                            Toast.makeText(MainActivity.this, "Please enter values of two distinct sides.",
                                    Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        switchMethod.setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {
                toggleMethodVisibility(position);
            }
        });
    }

    private void initAds() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("8C71F010EEAB0BA5FAEAB369132AF709")
                .build();
        adView.loadAd(adRequest);
    }

    private void toggleMethodVisibility(int position) {
        if (position == 0) {
            firstMethod.setVisibility(View.VISIBLE);
            secondMethod.setVisibility(View.GONE);
        } else {
            firstMethod.setVisibility(View.GONE);
            secondMethod.setVisibility(View.VISIBLE);
        }
    }

    private void handleFirstMethod() {
        int angleSwitch = switchAngle.getSelectedTab();
        int sideSwitch = switchSide.getSelectedTab();

        String angle, side;

        if (angleSwitch == 0)
            angle = "Phi";
        else
            angle = "Theta";

        side = MathUtils.assignSide(sideSwitch);

        double valueOfAngle;
        double valueOfSide;

        try {
            valueOfAngle = Double.valueOf(angleEditText.getText().toString());
            valueOfSide = Double.valueOf(sideEditText.getText().toString());
        } catch (Exception e) {
            return;
        }

        performCalculationsForFirstMethod(angle, side, valueOfAngle, valueOfSide);
    }

    private void performCalculationsForFirstMethod(String angle, String side,
                                                   double valueOfAngle, double valueOfSide) {
        double result[];
        if (angle.equals("Theta")) {
            result = MathUtils.performCalculationsForTheta(side, valueOfAngle, valueOfSide);
            setupResult(result);
        } else {
            result = MathUtils.performCalculationsForPhi(side, valueOfAngle, valueOfSide);
            setupResult(result);
        }

        FirstMethodModel calculation = new FirstMethodModel(switchAngle.getSelectedTab(),
                switchSide.getSelectedTab(), angleEditText.getText().toString(),
                sideEditText.getText().toString(), Arrays.toString(result));

        Gson gson = new Gson();

        TrigonometricRepo.initSharedPreferences(this);
        TrigonometricRepo.saveData(gson.toJson(calculation));

        // Add to database
        mViewModel.insertInFirstMethod(calculation);
    }

    private void handleSecondMethod() {
        int side1Switch = switchSide1.getSelectedTab();
        int side2Switch = switchSide2.getSelectedTab();

        String side1, side2;

        side1 = MathUtils.assignSide(side1Switch);
        side2 = MathUtils.assignSide(side2Switch);

        double valueOfSide1;
        double valueOfSide2;

        try {
            valueOfSide1 = Double.valueOf(side1EditText.getText().toString());
            valueOfSide2 = Double.valueOf(side2EditText.getText().toString());
        }catch (Exception e){
            return;
        }

        performCalculationsForSecondMethod(side1, side2, valueOfSide1, valueOfSide2);
    }

    private void performCalculationsForSecondMethod(String side1, String side2,
                                                    double valueOfSide1, double valueOfSide2) {
        double[] result = MathUtils.trigonometricCalculations(side1, side2, valueOfSide1, valueOfSide2);

        setupResult(result);

        SecondMethodModel calculation = new SecondMethodModel(switchSide1.getSelectedTab(),
                switchSide2.getSelectedTab(), side1EditText.getText().toString(),
                side2EditText.getText().toString(), Arrays.toString(result));

        mViewModel.insertInSecondMethod(calculation);
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

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.recent) {
            Intent intent = new Intent(this, RecentActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        toggleMethodVisibility(switchMethod.getSelectedTab());
        submitBtn.callOnClick();
    }
}
