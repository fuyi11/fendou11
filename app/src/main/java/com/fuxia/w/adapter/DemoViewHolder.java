package com.fuxia.w.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by fuyi on 2016/12/23.
 */

public class DemoViewHolder extends RecyclerView.ViewHolder{
    Button button;
    public DemoViewHolder(Context context) {
        super(new Button(context));

        button = (Button) itemView;
        button.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));

    }

    public void setText(String text){
        button.setText(text);
    }
}
