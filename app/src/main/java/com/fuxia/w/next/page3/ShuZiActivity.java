package com.fuxia.w.next.page3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.fuxia.w.R;

/**
 * Created by fuyi on 2017/8/2.
 */

public class ShuZiActivity extends AppCompatActivity {
    private IdentifyingCodeView icv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuzi);

        icv = (IdentifyingCodeView) findViewById(R.id.icv);

        icv.setInputCompleteListener(new IdentifyingCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                Log.i("icv_input", icv.getTextContent());
            }

            @Override
            public void deleteContent() {
                Log.i("icv_delete", icv.getTextContent());
            }
        });


    }

    public void onClick(View view) {
        icv.clearAllText();
    }
}
