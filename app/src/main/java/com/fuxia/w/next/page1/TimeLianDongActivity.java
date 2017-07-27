package com.fuxia.w.next.page1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.fuxia.w.R;

/**
 * Created by fuyi on 2017/3/28.
 */
public class TimeLianDongActivity extends AppCompatActivity {

    GuaView mGuaGuaKaView;
    GuaView mGuaGuaKaView2;

    RipClothes mRipClothes;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        mGuaGuaKaView = (GuaView) findViewById(R.id.st_guaguaka);
        mGuaGuaKaView.setOnGuaGuaKaCompletedListener(new GuaView.onGuaGuaKaCompletedListener() {
            @Override
            public void complete(String message) {
                Toast.makeText(TimeLianDongActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });

        mGuaGuaKaView2 = (GuaView) findViewById(R.id.st_guaguaka2);
        mGuaGuaKaView2.setOnGuaGuaKaCompletedListener(new GuaView.onGuaGuaKaCompletedListener() {
            @Override
            public void complete(String message) {
                Toast.makeText(TimeLianDongActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        mRipClothes = (RipClothes) findViewById(R.id.rc_rip);

        startActivity(new Intent(this, OverlayActivity.class));
    }
}
