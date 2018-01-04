package com.fuxia.w.next.page6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.fuxia.w.R;

/**
 * Created by fuyi on 2017/8/7.
 */

public class AutoFlowLayoutActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowlayout);
        Button normalFlow = (Button) findViewById(R.id.tv_normal_flow);
        Button specialFlow = (Button) findViewById(R.id.tv_special_flow);
        Button normalGrid = (Button) findViewById(R.id.tv_normal_grid);
        Button specialGrid = (Button) findViewById(R.id.tv_special_grid);
        normalFlow.setOnClickListener(this);
        specialFlow.setOnClickListener(this);
        normalGrid.setOnClickListener(this);
        specialGrid.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_normal_flow :{
                Intent intent = new Intent(AutoFlowLayoutActivity.this,NormalFlowActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.tv_special_flow :{
                Intent intent = new Intent(AutoFlowLayoutActivity.this,SpecialFlowActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.tv_normal_grid :{
                Intent intent = new Intent(AutoFlowLayoutActivity.this,NormalGridActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.tv_special_grid :{
                Intent intent = new Intent(AutoFlowLayoutActivity.this,SpecialGridActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
