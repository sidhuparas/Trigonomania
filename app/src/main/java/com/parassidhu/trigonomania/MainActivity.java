package com.parassidhu.trigonomania;

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

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.resultList) RecyclerView resultList;
    @BindView(R.id.submit_btn) Button submitBtn;
    @BindView(R.id.angle_et) MaterialEditText angleEditText;
    @BindView(R.id.side_et) MaterialEditText sideEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int valueOfAngle = Integer.valueOf(angleEditText.getText().toString());
                    int valueOfSide = Integer.valueOf(sideEditText.getText().toString());
                    setupResult(MathUtils.getTrigonometricValues(valueOfAngle, valueOfSide));
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Please fill correct values!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setupResult(float[] trigValues) {
        resultList.setLayoutManager(new GridLayoutManager(this, 2));

        MathAdapter adapter = new MathAdapter(trigValues);

        resultList.setAdapter(adapter);
    }
}
