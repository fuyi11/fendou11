package com.fuxia.w.view0;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.fuxia.w.R;
import com.fuxia.w.view.PtrrvGridViewMode;

/**
 * Created by fuyi on 2016/12/23.
 */
public class NewRecyclerViewActivity extends AppCompatActivity {
    private Button mBtnGridViewMode,mBtnListViewMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_newuser);
        findViews();

    }

    private void findViews(){
        mBtnGridViewMode = (Button) this.findViewById(R.id.btn_gv_mode);
        mBtnGridViewMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewRecyclerViewActivity.this.
                        startActivity(new Intent(NewRecyclerViewActivity.this, PtrrvGridViewMode.class));
            }
        });
        mBtnListViewMode = (Button) this.findViewById(R.id.btn_lv_mode);
        mBtnListViewMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewRecyclerViewActivity.this.
                        startActivity(new Intent(NewRecyclerViewActivity.this,PtrrvListViewMode.class));
            }
        });
    }

}

