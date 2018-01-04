package com.fuxia.w.next.page5.linear;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.fuxia.w.R;
import com.fuxia.w.next.page5.NormalDecoration;
import com.fuxia.w.next.page5.linear.datas.Car;
import com.fuxia.w.next.page5.linear.datas.CarsList;
import com.fuxia.w.next.page5.linear.datas.RecCarAdapter;

import java.util.List;


import butterknife.ButterKnife;
import butterknife.InjectView;


public class LinearCustomActivity extends AppCompatActivity {
    @InjectView(R.id.recView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        ButterKnife.inject(this);

        final List<Car> carList = CarsList.getCars();

        final RecCarAdapter adapter = new RecCarAdapter(this);
        adapter.addDatas(carList);


        final NormalDecoration decoration = new NormalDecoration() {
            @Override
            public String getHeaderName(int pos) {
                return carList.get(pos).getHeaderName();
            }
        };

        decoration.setOnHeaderClickListener(new NormalDecoration.OnHeaderClickListener() {
            @Override
            public void headerClick(int pos) {
                Toast.makeText(LinearCustomActivity.this, "点击到头部" + carList.get(pos).getHeaderName(), Toast.LENGTH_SHORT).show();
            }
        });
        decoration.setOnDecorationHeadDraw(new NormalDecoration.OnDecorationHeadDraw() {
            @Override
            public View getHeaderView(final int pos) {
                final View headView = LayoutInflater.from(LinearCustomActivity.this).inflate(R.layout.decoration_car_head_view, null);
                final ImageView ivAvatar = (ImageView) headView.findViewById(R.id.header_iv_avatar);
                decoration.loadImage(carList.get(pos).getLetter(), pos, ivAvatar);
                Log.e("QDX", "view inflate " + pos + "头部" + headView.hashCode());
                return headView;
            }
        });


        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
    }
}
