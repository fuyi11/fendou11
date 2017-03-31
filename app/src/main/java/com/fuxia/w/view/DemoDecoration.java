package com.fuxia.w.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by fuyi on 2016/12/23.
 */

public class DemoDecoration extends RecyclerView.ItemDecoration{
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int offset = (int) (12 * view.getContext().getResources().getDisplayMetrics().density);
        outRect.set(offset, offset, offset, offset);
    }
}
