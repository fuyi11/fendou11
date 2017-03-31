package com.fuxia.w.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fuxia.w.R;
import com.fuxia.w.bean.HomeCommentInfo;
import com.fuxia.w.view.NoScrollListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fuyi on 2016/12/26.
 */

public class StockCommentAdapter extends BaseAdapter {
    private String times, homeId, id, token;
    private String ziContent;
    private String ziName;
    private int resourceId;
    private Context context;
    private Handler handler;
    private ArrayList<HomeCommentInfo.CommentInfo> data;
    private ArrayList<HomeCommentInfo.Chd> data2;
    private LayoutInflater inflater;
    private EditText ziEdt;
    private CommentListener mCommentInfoListener;//加载监听


    public StockCommentAdapter(Context context, ArrayList<HomeCommentInfo.CommentInfo> data
            , ArrayList<HomeCommentInfo.Chd> data2
            , CommentListener commentlistener, EditText ziEdt
            , String homeId, String token, String id
            , int resourceId) {
        this.mCommentInfoListener = commentlistener;
        this.data = data;
        this.data2 = data2;
        this.context = context;
        this.resourceId = resourceId;
        this.ziEdt = ziEdt;
        this.homeId = homeId;
        this.token = token;
        this.id = id;

        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public HomeCommentInfo.CommentInfo getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        HomeCommentInfo.CommentInfo Info = data.get(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
//            convertView = View.inflate(CommonUtils.getContext(), R.layout.lv_stock_comment, null);
            // convertView = View.inflate(CommonUtils.getContext(), R.layout.lv_stock_comment1, null);
            convertView = View.inflate(context, R.layout.layout_comment, null);
            viewHolder = new ViewHolder();

            viewHolder.mTuBiao = (ImageView) convertView.findViewById(R.id.iv_stock_tubiao3);
            viewHolder.mItem1 = (TextView) convertView.findViewById(R.id.tv_stock_pl_item3);
            viewHolder.mTime = (TextView) convertView.findViewById(R.id.tv_stock_time3);
            viewHolder.content = (TextView) convertView.findViewById(R.id.tv_stock_content3);

            viewHolder.ll_sd_zipinglun = (LinearLayout) convertView.findViewById(R.id.lv_zi_pinglun3);
            viewHolder.fl_sd_coment = (FrameLayout) convertView.findViewById(R.id.fl_sd_coment);
            viewHolder.replyList = (NoScrollListView) convertView.findViewById(R.id.no_scroll_list_reply3);


            viewHolder.iv_message = (ImageView) convertView.findViewById(R.id.iv_edts3);
            viewHolder.tv_message = (TextView) convertView.findViewById(R.id.tv_edts3);

            viewHolder.iv_likes = (ImageView) convertView.findViewById(R.id.iv_likes3);
            viewHolder.tv_likes = (TextView) convertView.findViewById(R.id.tv_likes3);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final String ids = Info.getId();
        String add_time = Info.getAdd_time();
        final String content = Info.getContent();
        final String user_name = Info.getUser_name();
        String head_url = Info.getHead_url();
        final String dz_count = Info.getDz_count();
        final String count = Info.getChd_count();
        String chd_count = Info.getChd_count();


        viewHolder.content.setText(content);
        viewHolder.mItem1.setText(user_name);
        viewHolder.tv_message.setText(count);
        viewHolder.tv_likes.setText(dz_count);

        Times(add_time);
        viewHolder.mTime.setText(times);
//        ImageOptions imageOptions = new ImageOptions.Builder()
//                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
//                .setCrop(true)
//                .build();
//        String Url = Default.Image + head_url;
//        x.image().bind(viewHolder.mTuBiao, Url, imageOptions);


        List<HomeCommentInfo.Chd> chds = Info.getChd();
        for (int j = 0; j < chds.size(); j++) {
            data2.add(chds.get(j));
        }

        if (!chds.isEmpty()) {
            final ReplyAdapter adapter = new ReplyAdapter(context, chds, user_name, R.layout.item_pinglun2);
            viewHolder.replyList.setAdapter(adapter);
        } else {
            viewHolder.fl_sd_coment.setVisibility(View.GONE);
        }


        viewHolder.iv_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int keyboards = 1;
                mCommentInfoListener.StockCommentListener(position);
                mCommentInfoListener.StockCommentnetCode(keyboards);

                //keyBoardHint((Activity) context, ziEdt);

//                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromInputMethod(ziEdt.getWindowToken(),0);
            }
        });

//        viewHolder.iv_likes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    dolick();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        return convertView;
    }

//    private void dolick() throws JSONException {
//        RequestParams params = new RequestParams(Default.HomeComment);
//        params.addBodyParameter("type", 3 + "");
//        params.addBodyParameter("com_id", homeId);
//        params.addBodyParameter("uid", id);
//        params.addBodyParameter("u_token", token);
//        params.setAsJsonContent(true);
//
//        JSONObject json = new JSONObject();
//        json.put("type", 3 + "");
//        json.put("com_id", homeId);
//        json.put("uid", id);
//        json.put("u_token", token);
//        MyLog.e("上传数据" + json);
//        x.http().post(params, new Callback.CacheCallback<String>() {
//
//            @Override
//            public void onSuccess(String result) {
//                MyLog.e("获取到的数据是" + result);
//                try {
//                    JSONObject json = new JSONObject(result);
//                    if (json.getInt("status") == 0) {
//                        Toast.makeText(CommonUtils.getContext(), json.getString("message"), Toast.LENGTH_SHORT).show();
//                    } else if (json.getInt("status") == 1) {
//                        Toast.makeText(CommonUtils.getContext(), json.getString("message"), Toast.LENGTH_SHORT).show();
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                notifyDataSetChanged();
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                MyLog.e(">>>>>>>>>>>>>>>>>onError");
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//                MyLog.e(">>>>>>>>>>>onCancelled");
//            }
//
//            @Override
//            public void onFinished() {
//                MyLog.e(">>>>>>>>>>>>>>>onFinished");
//            }
//
//            @Override
//            public boolean onCache(String result) {
//                MyLog.e(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>onCache");
//                return false;
//            }
//        });
//    }

    public String Times(String add_time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        long lcc = Long.valueOf(add_time);
        int i = Integer.parseInt(add_time);
        times = sdr.format(new Date(i * 1000L));
        return times;
    }


    class ViewHolder {
        ImageView mTuBiao;
        TextView mItem1;
        TextView content;
        TextView mTime;
        TextView tv_message;
        ImageView iv_message;
        FrameLayout fl_sd_coment;

        TextView tv_likes;
        LinearLayout ll_sd_zipinglun;
        ImageView iv_likes;
        NoScrollListView replyList;
    }


    //2.创建一个获取回调监听接口的实现对象方法
    private CommentListener commentListener;

    public void setStockCommentListener(CommentListener stockCommentListener) {
        this.commentListener = stockCommentListener;
    }

    //1.创建回调监听
    public interface CommentListener {
        public void StockCommentListener(int position);

        public void StockCommentnetCode(int keyboards);

    }

//    public static void keyBoardHint(Activity activity, EditText editText) {
//        if (activity.getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED) {
//            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
//
//        }
//    }

}
