package com.parassidhu.trigonomania;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.parassidhu.trigonomania.adapters.CalculationsAdapter;
import com.parassidhu.trigonomania.model.FirstMethodModel;
import com.parassidhu.trigonomania.model.SecondMethodModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lib.kingja.switchbutton.SwitchMultiButton;

public class RecentActivity extends AppCompatActivity {

    @BindView(R.id.calculation_list) RecyclerView calculationList;
    @BindView(R.id.switch_method) SwitchMultiButton switchMethod;

    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();

        if (determineMethod() == 0)
            retrieveDataForFirstMethod();
        else
            retrieveDataForSecondMethod();
    }

    private void init() {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        calculationList.setLayoutManager(new LinearLayoutManager(RecentActivity.this));

        switchMethod.setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {
                if (position == 0)
                    retrieveDataForFirstMethod();
                else
                    retrieveDataForSecondMethod();
            }
        });
    }

    private int determineMethod() { return switchMethod.getSelectedTab(); }

    private Observer<List<FirstMethodModel>> firstObserver = new Observer<List<FirstMethodModel>>() {
        @Override
        public void onChanged(@Nullable List<FirstMethodModel> list) {
            if (list != null) {
                CalculationsAdapter adapter = new CalculationsAdapter(list, true, 0);
                calculationList.setAdapter(adapter);
            }
        }
    };

    private Observer<List<SecondMethodModel>> secondObserver = new Observer<List<SecondMethodModel>>() {
        @Override
        public void onChanged(@Nullable List<SecondMethodModel> list) {
            if (list != null) {
                CalculationsAdapter adapter = new CalculationsAdapter(list, true, 1);
                calculationList.setAdapter(adapter);
            }
        }
    };

    private void retrieveDataForFirstMethod() {
        mViewModel.getSecondMethodData().removeObserver(secondObserver);
        mViewModel.getFirstMethodData().observe(this, firstObserver);
    }

    private void retrieveDataForSecondMethod() {
        mViewModel.getFirstMethodData().removeObserver(firstObserver);
        mViewModel.getSecondMethodData().observe(this, secondObserver);
    }
}
