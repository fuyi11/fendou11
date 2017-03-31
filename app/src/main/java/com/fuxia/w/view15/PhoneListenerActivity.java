package com.fuxia.w.view15;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by fuyi on 2017/3/15.
 */
public class PhoneListenerActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在这个方法里面开启服务
        Intent intent1 = new Intent(this,PhoneStateService.class);
        startService(intent1);
    }
}
