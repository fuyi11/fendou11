package com.fuxia.w.next.page5.grid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.fuxia.w.R;
import com.fuxia.w.next.page5.GridDecoration;
import com.fuxia.w.next.page5.NormalDecoration;
import com.fuxia.w.next.page5.grid.datas.RecWomenAdapter;
import com.fuxia.w.next.page5.grid.datas.Women;
import com.fuxia.w.next.page5.grid.datas.WomenList;

import java.util.List;


import butterknife.ButterKnife;
import butterknife.InjectView;


public class GridNormalActivity extends AppCompatActivity {
    @InjectView(R.id.recView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        ButterKnife.inject(this);

        final List<Women> womens = WomenList.getWomen();

        RecWomenAdapter adapter = new RecWomenAdapter(this);
        adapter.addDatas(womens);

        final GridDecoration decoration = new GridDecoration(womens.size(), 3) {
            @Override
            public String getHeaderName(int pos) {
                return womens.get(pos).getName();
            }
        };

        decoration.setOnHeaderClickListener(new NormalDecoration.OnHeaderClickListener() {
            @Override
            public void headerClick(int pos) {

                Toast.makeText(GridNormalActivity.this, "点击到头部" + pos, Toast.LENGTH_SHORT).show();
            }
        });

        GridLayoutManager manager = new GridLayoutManager(this, 3);
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);

    }
}
