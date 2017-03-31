package com.fuxia.w.view9;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fuxia.w.R;

import rx.Observer;

/**
 * Created by Bob on 2017/1/3.
 */

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv2 = (TextView) findViewById(R.id.tv2);

        getDate();
    }

    public void getDate() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };

    }
}
