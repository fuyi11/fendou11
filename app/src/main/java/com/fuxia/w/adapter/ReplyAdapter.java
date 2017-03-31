package com.fuxia.w.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.fuxia.w.R;
import com.fuxia.w.bean.HomeCommentInfo;

import java.util.List;

/**
 * Created by fuyi on 2016/12/26.
 */

public class ReplyAdapter extends BaseAdapter {
    private int resourceId;
    private List<HomeCommentInfo.Chd> list;
    private LayoutInflater inflater;
    private TextView replyContent;
    private SpannableString ss;
    private Context context;
    private String user_name;
    public ReplyAdapter(Context context, List<HomeCommentInfo.Chd> list
            , String user_name,
                        int resourceId){
        this.list = list;
        this.context = context;
        this.user_name = user_name;
        this.resourceId = resourceId;
        inflater = LayoutInflater.from(context);


    }


    @Override
    public int getCount() {
        //MyLog.e("        传过来了  list.size():" +list);
        return list.size();


    }

    @Override
    public HomeCommentInfo.Chd getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeCommentInfo.Chd bean = list.get(position);
        convertView = inflater.inflate(resourceId, null);
        TextView mName = (TextView) convertView.findViewById(R.id.tv_sd_name);
        TextView mName2 = (TextView) convertView.findViewById(R.id.tv_sd_huifu_name);
        TextView mConten = (TextView) convertView.findViewById(R.id.tv_sd_content);

        String add_time = list.get(position).getAdd_time();
        String content = list.get(position).getContent();
        String Ziname = list.get(position).getUser_name();

        mConten.setText(content);
        mName.setText(Ziname);
        mName2.setText(user_name);
        return convertView;
    }

    private final class TextSpanClick extends ClickableSpan {
        private boolean status;
        public TextSpanClick(boolean status){
            this.status = status;
        }
        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);//取消下划线
        }
        @Override
        public void onClick(View v) {
            String msgStr = "";
            if(status){
                msgStr = "我是回复的人";
            }else{
                msgStr = "我是评论的人";
            }
            Toast.makeText(context, msgStr, Toast.LENGTH_SHORT).show();
        }
    }

    private final class ListItemClick implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }
}
