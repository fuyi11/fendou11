package com.fuxia.w.view0;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.fuxia.w.R;
import com.fuxia.w.adapter.AnimalsAdapter;
import com.fuxia.w.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import com.fuxia.w.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.fuxia.w.stickyheadersrecyclerview.StickyRecyclerHeadersTouchListener;
import com.fuxia.w.view2.DividerDecoration;
import com.fuxia.w.view2.RecyclerItemClickListener;

import java.security.SecureRandom;

/**
 * Created by fuyi on 2016/12/26.
 */
public class HeadRecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_head_rv);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        Button button = (Button) findViewById(R.id.button_update);
        final ToggleButton isReverseButton = (ToggleButton) findViewById(R.id.button_is_reverse);

        // Set adapter populated with example dummy data
        final AnimalsHeadersAdapter adapter = new AnimalsHeadersAdapter();
        adapter.add("以下动物!");
        adapter.addAll(getDummyDataSet());
        recyclerView.setAdapter(adapter);

        // Set button to update all views one after another (Test for the "Dance")
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler(Looper.getMainLooper());
                for (int i = 0; i < adapter.getItemCount(); i++) {
                    final int index = i;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyItemChanged(index);
                        }
                    }, 50);
                }
            }
        });

        // 设置布局管理器
        int orientation = getLayoutManagerOrientation(getResources().getConfiguration().orientation);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, orientation, isReverseButton.isChecked());
        recyclerView.setLayoutManager(layoutManager);

        // Add the sticky headers decoration 添加粘性标题装饰
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(adapter);
        recyclerView.addItemDecoration(headersDecor);

        // Add decoration for dividers between list items
        //添加列表项之间的分隔装饰
        recyclerView.addItemDecoration(new DividerDecoration(this));

        // Add touch listeners 添加触摸的监听
        StickyRecyclerHeadersTouchListener touchListener =
                new StickyRecyclerHeadersTouchListener(recyclerView, headersDecor);
        touchListener.setOnHeaderClickListener(
                new StickyRecyclerHeadersTouchListener.OnHeaderClickListener() {
                    @Override
                    public void onHeaderClick(View header, int position, long headerId) {
                        Toast.makeText(HeadRecyclerViewActivity.this, "Header position: " + position + ", id: " + headerId,
                                Toast.LENGTH_SHORT).show();
                    }
                });
        recyclerView.addOnItemTouchListener(touchListener);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                adapter.remove(adapter.getItem(position));
            }
        }));
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                headersDecor.invalidateHeaders();
            }
        });

        isReverseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = isReverseButton.isChecked();
                isReverseButton.setChecked(isChecked);
                layoutManager.setReverseLayout(isChecked);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private String[] getDummyDataSet() {
        return getResources().getStringArray(R.array.animals);
    }

    private int getLayoutManagerOrientation(int activityOrientation) {
        if (activityOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            return LinearLayoutManager.VERTICAL;
        } else {
            return LinearLayoutManager.HORIZONTAL;
        }
    }

    private class AnimalsHeadersAdapter extends AnimalsAdapter<RecyclerView.ViewHolder>
            implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_item, parent, false);
            return new RecyclerView.ViewHolder(view) {
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            TextView textView = (TextView) holder.itemView;
            textView.setText(getItem(position));
        }

        @Override
        public long getHeaderId(int position) {
            if (position == 0) {
                return -1;
            } else {
                return getItem(position).charAt(0);
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_header, parent, false);
            return new RecyclerView.ViewHolder(view) {
            };
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
            TextView textView = (TextView) holder.itemView;
            textView.setText(String.valueOf(getItem(position).charAt(0)));
            holder.itemView.setBackgroundColor(getRandomColor());
        }

        private int getRandomColor() {
            SecureRandom rgen = new SecureRandom();
            return Color.HSVToColor(150, new float[]{
                    rgen.nextInt(359), 1, 1
            });
        }

    }
}
