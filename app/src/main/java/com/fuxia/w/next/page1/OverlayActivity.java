package com.fuxia.w.next.page1;

import android.app.Activity;
import android.os.Bundle;

import com.fuxia.w.R;

/**
 * Created by fuyi on 2017/7/13.
 */

public class OverlayActivity extends Activity {

    private OverlayImageView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mView = new OverlayImageView(this);
        setContentView(mView);

        mView.setImages(R.drawable.num_01, R.drawable.num_02, R.drawable.num_03,
                R.drawable.num_04, R.drawable.num_05);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        System.out.println(mView.getWidth());
    }
}
