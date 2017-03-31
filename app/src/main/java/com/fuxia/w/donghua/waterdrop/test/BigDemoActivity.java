package com.fuxia.w.donghua.waterdrop.test;

import android.app.Activity;
import android.os.Bundle;

import com.fuxia.w.R;
import com.fuxia.w.donghua.waterdrop.CoverManager;


public class BigDemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big);

        getActionBar().hide();
        CoverManager.getInstance().init(this);

        CoverManager.getInstance().setMaxDragDistance(350);
        CoverManager.getInstance().setExplosionTime(150);
    }

}
