package com.fuxia.w.next.page10;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;

import com.fuxia.w.R;

import java.io.File;

/**
 * Created by fuyi on 2017/8/11.
 */

public class PopDialog extends Dialog  {

    private final int REQUEST_ALBUM = 101;
    private final int REQUEST_PHOTO = 102;

    private Context context;
    private Window window;
//    private RelativeLayout mCamero;
//    private RelativeLayout mPhotos;
//    private RelativeLayout mDeselect;

    private final long currentTimeMillis = System.currentTimeMillis();
    private File tempfile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + currentTimeMillis + "photo_temp.jpg");

    public PopDialog(Context context) {
        super(context, R.style.AddressStyleDialogTheme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        window = getWindow();
        this.setContentView(R.layout.popdialog);//给对话框加载页面

        // 修改对话框显示的位置
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;//中心_水平
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public void setOnButtonClickListener(View.OnClickListener listener) {
        findViewById(R.id.rl_dialog_camero).setOnClickListener(listener);
        findViewById(R.id.rl_dialog_photos).setOnClickListener(listener);
        findViewById(R.id.rl_dialog_deselect).setOnClickListener(listener);
    }

    public void animat() {
        ValueAnimator va = ValueAnimator.ofInt(0, 210);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (Integer) valueAnimator.getAnimatedValue();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, value);
            }
        });

        va.setInterpolator(new OvershootInterpolator());
        va.setDuration(1000);
        va.start();
    }

}
