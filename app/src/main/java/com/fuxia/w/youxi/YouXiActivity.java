package com.fuxia.w.youxi;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.fuxia.w.R;
import com.fuxia.w.wuziqi.WuZiQiActivity;

/**
 * Created by fuyi on 2017/3/23.
 */
public class YouXiActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_youxi);
        Button mWuZiQi = (Button) findViewById(R.id.btn_wuziqi);
        mWuZiQi.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_wuziqi:
                startActivity(new Intent(YouXiActivity.this, WuZiQiActivity2.class));
                break;
        }
    }
}
