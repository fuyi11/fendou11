package com.fuxia.w.next.page5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fuxia.w.R;
import com.fuxia.w.next.page5.grid.GridCustomActivity;
import com.fuxia.w.next.page5.grid.GridNormalActivity;
import com.fuxia.w.next.page5.linear.LinearCustomActivity;
import com.fuxia.w.next.page5.linear.LinearNormalActivity;

/**
 * Created by fuyi on 2017/8/4.
 */

public class zhanXingtouActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhanxing);

    }

    public void btn_lly_normal(View view) {
        startActivity(new Intent(zhanXingtouActivity.this, LinearNormalActivity.class));
    }

    public void btn_lly_custom(View view) {
        startActivity(new Intent(zhanXingtouActivity.this, LinearCustomActivity.class));
    }

    public void btn_grid_normal(View view) {
        startActivity(new Intent(zhanXingtouActivity.this, GridNormalActivity.class));
    }

    public void btn_grid_custom(View view) {
        startActivity(new Intent(zhanXingtouActivity.this, GridCustomActivity.class));
    }
}
