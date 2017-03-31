package com.fuxia.w.view0;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fuxia.w.R;

/**
 * Created by fuyi on 2016/12/22.
 */
public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

    }

    public void onListViewClick(View view) {
        startActivity(new Intent(this, ListViewActivity.class));
    }

    public void onRecyclerViewClick(View view) {
        startActivity(new Intent(this, RecyclerViewActivity.class));
    }

    public void onScrollViewClick(View view) {
        startActivity(new Intent(this, ScrollViewActivity.class));
    }

}
