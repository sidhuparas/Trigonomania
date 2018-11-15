package com.parassidhu.trigonomania;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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
    @BindView(R.id.empty_tv) TextView emptyTv;

    private MainViewModel mViewModel;
    private List<FirstMethodModel> firstList;
    private List<SecondMethodModel> secondList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();

        setData(determineMethod());
    }

    private void setData(int i) {
        if (i == 0)
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
                setData(position);
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
                firstList = list;

                if (list.size() == 0) showEmptyState(true);
                else  showEmptyState(false);
            }
        }
    };

    private Observer<List<SecondMethodModel>> secondObserver = new Observer<List<SecondMethodModel>>() {
        @Override
        public void onChanged(@Nullable List<SecondMethodModel> list) {
            if (list != null) {
                CalculationsAdapter adapter = new CalculationsAdapter(list, true, 1);
                calculationList.setAdapter(adapter);
                secondList = list;

                if (list.size() == 0) showEmptyState(true);
                else  showEmptyState(false);
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

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setData(determineMethod());
    }

    private void showEmptyState(boolean show){
        if (show)
            emptyTv.setVisibility(View.VISIBLE);
        else
            emptyTv.setVisibility(View.GONE);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_recent, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.clear_searches){
            if (firstList != null)
                mViewModel.deleteAllFirstMethod(firstList);

            if (secondList != null)
                mViewModel.deleteAllSecondMethod(secondList);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
