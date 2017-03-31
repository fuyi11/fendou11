package com.fuxia.w;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.fuxia.w.liandong.LianDongActivity;
import com.fuxia.w.next.page1.TimeLianDongActivity;
import com.fuxia.w.view0.DemoActivity;

/**
 * Created by fuyi on 2017/3/27.
 */
public class NextPageActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nextpager);
        Button mButton1 = (Button) findViewById(R.id.nx_buttons1);
        Button mButton2 = (Button) findViewById(R.id.nx_buttons2);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nx_buttons1:
                Intent intent = new Intent(NextPageActivity.this,LianDongActivity.class);
                startActivity(intent);
                break;
            case R.id.nx_buttons2:
                Intent intent2 = new Intent(NextPageActivity.this,TimeLianDongActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
