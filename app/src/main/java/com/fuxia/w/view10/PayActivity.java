package com.fuxia.w.view10;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.fuxia.w.R;
import com.fuxia.w.view11.PayDialog;

/**
 * Created by fuyi on 2017/2/9.
 */
public class PayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pay);

        TextView mTitle = (TextView) findViewById(R.id.tv_title);
        Button mButton = (Button) findViewById(R.id.btn_pay);
        mTitle.setText("测试游戏SDK演示");

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDoesDialog();
            }
        });

    }

    private void showDoesDialog() {
        PayDialog pd = new PayDialog(PayActivity.this);
        Window win = pd.getWindow();
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.y = 200;//设置y坐标
        win.setAttributes(params);
        pd.setCancelable(true);//是否可以按返回键取消
        pd.setCanceledOnTouchOutside(false);
        pd.show();
    }
}
