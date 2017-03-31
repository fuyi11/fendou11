package com.fuxia.w.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fuxia.w.R;

import java.util.ArrayList;

/**
 * Created by fuyi on 2016/12/22.
 */
 public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<String> datass;

    public MyAdapter(ArrayList<String> list) {
        datass = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (holder == null) {
            System.out.print("holder null");
        }
        if (holder.item == null) {
            System.out.print("holder item null");
        }
        if (datass == null) {
            System.out.print("datass null");
        }
        holder.item.setText(datass.get(position));
    }

    @Override
    public int getItemCount() {
        return datass.size();
    }


    public void addItem(ArrayList<String> list2) {

        list2.addAll(datass);
        datass.removeAll(datass);
        datass.addAll(list2);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView item;

        public MyViewHolder(View itemView) {
            super(itemView);
            item = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }
}
