package com.parassidhu.trigonomania;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.resultList) RecyclerView resultList;
    @BindView(R.id.submit_btn) Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupResult();
            }
        });

    }

    private void setupResult() {
        resultList.setLayoutManager(new GridLayoutManager(this, 2));

        MathAdapter adapter = new MathAdapter();

        resultList.setAdapter(adapter);
    }
}
