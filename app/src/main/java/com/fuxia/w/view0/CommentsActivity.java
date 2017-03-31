package com.fuxia.w.view0;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.fuxia.w.R;
import com.fuxia.w.adapter.StockCommentAdapter;
import com.fuxia.w.bean.HomeCommentInfo;

import java.util.ArrayList;

/**
 * Created by fuyi on 2016/12/26.
 */
public class CommentsActivity extends AppCompatActivity{
    private ListView mComment_listview;
    private RadioButton mComent_button0;
    private RadioButton mComent_button1;
    private RadioButton mComent_button2;


    private ArrayList<HomeCommentInfo.CommentInfo> data = new ArrayList<>();
    private ArrayList<HomeCommentInfo.Chd> data2 = new ArrayList<>();
    String homeId, id, token;
    private StockCommentAdapter commentAdapter;
    private RelativeLayout ziRelativeLayoutSend;
    private EditText ziEdt;
    private Button ziBtnSend;
    private ScrollView mScrollView;

    private int count;                    //记录评论ID
    private String comment = "";        //记录对话框中的内容
    private int position;                //记录回复评论的索引
    private boolean isReply;            //是否是回复，true代表回复
    private StockCommentAdapter.CommentListener commentListener;
    private String ids;
    private Context context;
    int returns = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_stockcomment);
        context = CommentsActivity.this;


    mComment_listview = (ListView) findViewById(R.id.lv_stockcomment_listview);
    mComent_button0 = (RadioButton) findViewById(R.id.rb_button);
    ImageView Edt = (ImageView) findViewById(R.id.iv_pl_edt);
    ziRelativeLayoutSend = (RelativeLayout) findViewById(R.id.rl_sd_fasong);
    ziEdt = (EditText) findViewById(R.id.et_sd_huifu2);
    ziBtnSend = (Button) findViewById(R.id.btn_sd_send);
    mScrollView = (ScrollView) findViewById(R.id.sl_sk);
//    mComent_button0.setOnClickListener(this);
//    Edt.setOnClickListener(this);
//        ziBtnSend.setOnClickListener(this);
//

    Intent intent = getIntent();
    homeId = intent.getStringExtra("stockId");

//    SharedPreferences sp = getSharedPreferences(Default.SP_NAME, CommonUtils.getContext().MODE_PRIVATE);
//    id = sp.getString("id", "");
//    token = sp.getString("u_token", "");

//    try {
//        doHttp();
//    } catch (JSONException e) {
//        e.printStackTrace();
//    }
//
//    commentListener = new StockCommentAdapter.CommentListener() {
//        @Override
//        public void StockCommentListener(int position) {
//            ids = data.get(position).getId();
//
//            // MethodManager();
//            mComent_button0.setVisibility(View.GONE);
//            mScrollView.setVisibility(View.VISIBLE);
//            ziRelativeLayoutSend.setVisibility(View.VISIBLE);
////
//            ziBtnSend.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    String plConrter = ziEdt.getText().toString().trim();
//                    if (plConrter.equals("")) {
//                       // Toast.makeText(CommonUtils.getContext(), "回复评论不能为空", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    try {
//                        doSend(ids, plConrter);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//
//
//        }
//
//
//        int keyboards;
//
//        @Override
//        public void StockCommentnetCode(int keyboards) {
//            this.keyboards = keyboards;
//          //  MyLog.e("键盘keyboards "+keyboards);
//            if(keyboards== 1){
//                // MethodManager();
//                onFocusChange(true);
//                returns = 1;
//            }
//        }
//    };
//
//    commentAdapter = new StockCommentAdapter(this, data, data2, commentListener, ziEdt, homeId, token, id
//            , R.layout.lv_stock_comment);
//    mComment_listview.setAdapter(commentAdapter);
//    commentAdapter.notifyDataSetChanged();
//
//}
//
//
//    @Override
//    protected void onResume() {
//
//        try {
//            doHttp();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        super.onResume();
//    }
//
//    public void MethodManager() {
////        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
////        if (imm != null) {
////            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
////        }
//        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
//        imm. toggleSoftInput (0, InputMethodManager.HIDE_NOT_ALWAYS);
//    }
//
//    // 监听返回键
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(returns == 1){
//            if (keyCode == KeyEvent.KEYCODE_BACK
//                    && event.getRepeatCount() == 0) {
//                mComent_button0.setVisibility(View.VISIBLE);
//                mScrollView.setVisibility(View.GONE);
//                ziRelativeLayoutSend.setVisibility(View.GONE);
//               // MyLog.e("returns"+returns);
//                return true;
//            }
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.iv_pl_edt:
//                Intent intent = new Intent(CommonUtils.getContext(), EndetCommentActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("stockId", homeId);
//                startActivity(intent);
//                finish();
//                break;
//            case R.id.rb_button:
//                finish();
//                break;
//        }
    }

//    private void doSend(String ids, String plConrter) throws JSONException {
//
//        RequestParams params = new RequestParams(Default.HomeComment);
//        params.setAsJsonContent(true);
//        params.addBodyParameter("type", 2 + "");
//        params.addBodyParameter("uid", id);
//        params.addBodyParameter("u_token", token);
//        params.addBodyParameter("pro_id", homeId);
//        params.addBodyParameter("content", plConrter);
//        params.addBodyParameter("pid", ids);
//
//        JSONObject json = new JSONObject();
//        json.put("type", 2 + "");
//        json.put("uid", id);
//        json.put("u_token", token);
//        json.put("pro_id", homeId);
//        json.put("content", plConrter);
//        json.put("pid", ids);
//        MyLog.e("上传数据 回复：" + json);
//
//        x.http().post(params, new Callback.CacheCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                MyLog.e("获取到 回复 的数据是：" + "(" + result + ")");
//                try {
//                    JSONObject object = new JSONObject(result);
//                    if (object.getInt("status") == 0) {
//                        Toast.makeText(CommentsActivity.this, object.getString("message"), Toast.LENGTH_SHORT).show();
//                    } else if (object.getInt("status") == 1) {
//                        Toast.makeText(CommentsActivity.this, object.getString("message"), Toast.LENGTH_SHORT).show();
//                    }
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                mComent_button0.setVisibility(View.VISIBLE);
//                mScrollView.setVisibility(View.GONE);
//                ziRelativeLayoutSend.setVisibility(View.GONE);
//                onFocusChange(false);
//                try {
//                    doHttp();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                commentAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                MyLog.e("<<<<<<<<<<<<<<<<<<<<<<<<<<<onError");
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//                MyLog.e("<<<<<<<<<<<<<<<<<<<<<<<<<<<onCancelled");
//            }
//
//            @Override
//            public void onFinished() {
//
//                MyLog.e("<<<<<<<<<<<<<<<<<<<<<<<<<<<onFinished");
//                commentAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public boolean onCache(String result) {
//                MyLog.e("<<<<<<<<<<<<<<<<<<<<<<<<<<<onCache");
//                return false;
//            }
//        });
//    }
//
//
//    private void doHttp() throws JSONException {
//
//        RequestParams params = new RequestParams(Default.HomeComment);
//        params.addBodyParameter("type", 1 + "");
//        params.addBodyParameter("id", homeId);
//        params.setAsJsonContent(true);
//
//        JSONObject json = new JSONObject();
//        json.put("type", 1 + "");
//        json.put("id", homeId);
//        MyLog.e("上传数据" + json);
//        x.http().post(params, new Callback.CacheCallback<String>() {
//
//            @Override
//            public void onSuccess(String result) {
//
//                MyLog.e("获取到的评论的数据是：" + result);
//                data.clear();
//                Gson gson = new Gson();
//                java.lang.reflect.Type type = new TypeToken<HomeCommentInfo>() {
//                }.getType();
//                HomeCommentInfo jsonBean = gson.fromJson(result, type);
//                MyLog.e("jsonBean                        jsonBean          " + jsonBean);
//                for (int i = 0; i < jsonBean.getData().size(); i++) {
//                    data.add(jsonBean.getData().get(i));
//                }
//
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
//
//            }
//
//            @Override
//            public void onFinished() {
//                commentAdapter.notifyDataSetChanged();
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


    /**
     * 显示或隐藏输入法
     */
    private void onFocusChange(boolean hasFocus) {
        final boolean isFocus = hasFocus;
        (new Handler()).postDelayed(new Runnable() {
            public void run() {
                InputMethodManager imm = (InputMethodManager)
                        ziEdt.getContext().getSystemService(INPUT_METHOD_SERVICE);
                if (isFocus) {
                    //显示输入法
                    ziEdt.requestFocus();//获取焦点
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                } else {
                    //隐藏输入法
                    imm.hideSoftInputFromWindow(ziEdt.getWindowToken(), 0);
                    mComent_button0.setVisibility(View.VISIBLE);
                    mScrollView.setVisibility(View.GONE);
                    ziRelativeLayoutSend.setVisibility(View.GONE);

                }
            }
        }, 100);
    }
}
