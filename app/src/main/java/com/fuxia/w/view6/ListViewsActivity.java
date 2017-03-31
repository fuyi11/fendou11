package com.fuxia.w.view6;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


import com.fuxia.w.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuyi on 2017/1/3.
 */
public class ListViewsActivity extends AppCompatActivity {

        private List<String> mList=new ArrayList<String>();
        private int mPageNo=0;
        private int mPageNum=10;
        private SwipeRefreshLayout swipeRefreshLayout;
        private LoadMoreListView mListView;

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_listview);

            mListView=(LoadMoreListView) findViewById(R.id.listViewss);
            mListView.setFooterView(View.inflate(this, R.layout.item_listview_footer_loadmore, null));
            final ArrayAdapter<String> mAdapter=new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_expandable_list_item_1, mList);
            mListView.setAdapter(mAdapter);
            mListView.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
                @Override
                public void onLoad() {
                    loadMore();
                    mAdapter.notifyDataSetChanged();
                }
            });

            swipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.refreshviewss);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    reFresh();
                    mAdapter.notifyDataSetChanged();
                }
            });
            reFresh();
        }

    private void reFresh() {
        mPageNo=0;
        mList.clear();
        mListView.setEnd(false);
        for(int i=mPageNo*mPageNum;i<(mPageNo+1)*mPageNum;i++) {
            mList.add(""+i);
        }
        mPageNo++;
        swipeRefreshLayout.setRefreshing(false);
    }

    private void loadMore() {
        if(mList.size()<30) {
            for(int i=mPageNo*mPageNum;i<(mPageNo+1)*mPageNum;i++) {
                mList.add(""+i);
            }
            mPageNo++;
        }
        else {
            mListView.setEnd(true);
        }
    }



}
