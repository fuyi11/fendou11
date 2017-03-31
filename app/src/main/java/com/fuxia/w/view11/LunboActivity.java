package com.fuxia.w.view11;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.fuxia.w.CommonUtils;
import com.fuxia.w.R;
import com.fuxia.w.ToastUtils;
import com.fuxia.w.v12.Kanner;

/**
 * Created by fuyi on 2017/2/17.
 */
public class LunboActivity extends AppCompatActivity{
    private Kanner kanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanner);
        kanner = (Kanner) findViewById(R.id.kanner);
        // kanner.setImagesUrl(new String[] {
        // "http://img04.muzhiwan.com/2015/06/16/upload_557fd293326f5.jpg",
        // "http://img03.muzhiwan.com/2015/06/05/upload_557165f4850cf.png",
        // "http://img02.muzhiwan.com/2015/06/11/upload_557903dc0f165.jpg",
        // "http://img04.muzhiwan.com/2015/06/05/upload_5571659957d90.png",
        // "http://img03.muzhiwan.com/2015/06/16/upload_557fd2a8da7a3.jpg" });
        int[] imagesRes = {R.mipmap.a, R.mipmap.b, R.mipmap.c,
                R.mipmap.d, R.mipmap.e};

        kanner.setImagesRes(imagesRes);


    }

    @Override
    protected void onDestroy() {
        kanner.removeCallbacksAndMessages();
        super.onDestroy();
    }
}
