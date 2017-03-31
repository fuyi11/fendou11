package com.fuxia.w.wuziqi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fuxia.w.R;

/**
 * Created by fuyi on 2017/3/22.
 */
public class WuZiQiActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChessFiveView view=new ChessFiveView(this);
        view.setBackgroundResource(R.drawable.main_bg);
        setContentView(view);




    }


}
