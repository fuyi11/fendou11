package com.fuxia.w.view11;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.fuxia.w.R;

/**
 * Created by fuyi on 2017/2/9.
 */
public class PayDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private Window window;

    public PayDialog(Context context) {
        super(context, R.style.AddressStyleDialogTheme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        window = getWindow();
        this.setContentView(R.layout.layout_paydialog);
        findViewById(R.id.tv_d_close);
        ImageView mClose = (ImageView) findViewById(R.id.tv_d_close);
        mClose.setOnClickListener(this);
        initView();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_d_close:
                dismiss();
                break;
        }
    }


    private void initView() {
        // 修改对话框显示的位置
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = Gravity.TOP;//
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        WindowManager m = window.getWindowManager();
        Display d = m.getDefaultDisplay(); // 为获取屏幕宽、高
        WindowManager.LayoutParams p = getWindow().getAttributes(); // 获取对话框当前的参值
        p.height = (int) (d.getHeight() * 0.23); // 高度设置为屏幕的
        p.width = (int) (d.getWidth() * 0.89); // 宽度设置为屏幕的0.8
//        p.alpha = 1.0f; // 设置本身透明度
//        p.dimAmount = 0.0f; // 设置黑暗度
        getWindow().setAttributes(p); // 设置生效
//        getWindow().setGravity(Gravity.RIGHT); // 设置靠右对齐
    }
}
