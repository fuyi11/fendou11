package com.fuxia.w.view3;

import android.os.Bundle;

import com.fuxia.w.R;

/**
 * Created by fuyi on 2016/12/27.
 */

public class PostCreatedActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_created);
        onIssues(this, getString(R.string.post_request_ok));
    }

}
