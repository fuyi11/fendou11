package com.fuxia.w.next.page11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.fuxia.w.R;

/**
 * Created by fuyi on 2017/8/29.
 */

public class NewQianDaoActivity extends AppCompatActivity {
    private ImitateKeepButton imitateKeepButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newqiandao);
        imitateKeepButton = (ImitateKeepButton) findViewById(R.id.imitateKeepButton);
        imitateKeepButton.setOnViewClick(new ImitateKeepButton.OnViewClick() {
            @Override
            public void onFinish(View view) {
                Toast.makeText(NewQianDaoActivity.this,"签到完成！",Toast.LENGTH_SHORT).show();
                //    imitateKeepButton.setCircleColor(Color.GRAY);
                imitateKeepButton.setContentText("完成");
                // imitateKeepButton.setEnabled(false);
            }
        });
    }
}
