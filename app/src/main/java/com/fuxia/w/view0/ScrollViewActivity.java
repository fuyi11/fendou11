package com.fuxia.w.view0;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baoyz.widget.PullRefreshLayout;
import com.baoyz.widget.SmartisanDrawable;
import com.fuxia.w.R;

/**
 * Created by fuyi on 2016/12/22.
 */
public class ScrollViewActivity extends AppCompatActivity{
    PullRefreshLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);

        layout = (PullRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                layout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        layout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        layout.setColorSchemeColors(Color.GRAY);
        layout.setRefreshDrawable(new SmartisanDrawable(this, layout));
    }
}
