package com.fuxia.w.view0;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.fuxia.w.R;
import com.fuxia.w.view.LoadMoreRecyclerView;
import com.fuxia.w.view.MySwipeRefreshLayout;
import com.fuxia.w.adapter.MyAdapter;

import java.util.ArrayList;

/**
 * Created by fuyi on 2016/12/22.
 */
public class MyRecyclerViewActivity extends AppCompatActivity {

    private LoadMoreRecyclerView view;
    private ArrayList<String> list;

    private MyAdapter adapter;
    private MySwipeRefreshLayout refreshLayout;
    private ArrayList<String> list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_myrv);
        list = new ArrayList<>();
        refreshLayout = (MySwipeRefreshLayout) findViewById(R.id.refresh_layout);

        list.clear();
        list.add("苍老师");
        list.add("小泽老师");
        list.add("波波老师");

        refreshLayout.setColorSchemeResources(R.color.indicator,
                R.color.colorAccent,
                R.color.orange,
                R.color.red);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        initViews();
                        refreshLayout.setRefreshing(false);
                        adapter.notifyDataSetChanged();

                    }
                }, 2000);
            }
        });


        view = (LoadMoreRecyclerView) findViewById(R.id.recycler);
        view.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter(list);
        view.setAdapter(adapter);
        final Handler handler = new Handler();

        view.setOnLoadingListener(new LoadMoreRecyclerView.onLoadingMoreListener() {
            @Override
            public void onLoading() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.add("堀北真希");
                        adapter.notifyItemRangeChanged(0, list.size());
                        view.loadFinished();
                    }
                }, 2000);
            }
        });
    }

    private void initViews() {
        list2 = new ArrayList<>();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                list2.add("下拉1");
                adapter.addItem(list2);
            }
        }, 3000);
    }
}
