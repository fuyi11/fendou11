package com.fuxia.w;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.fuxia.w.view11.CretinAutoUpdateUtils;
import com.weavey.loading.lib.LoadingLayout;

/**
 * Created by fuyi on 2017/2/14.
 */

public class MyApplication extends Application {
    private static MyApplication mInstance;

    // 上下文菜单

    private static Context mContext;
    // 记录是否已经初始化
    private boolean isInit = false;
    private static Handler mainHanlder;
    private static int myTid;

    public static String currentUserNick = "";

    private static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化友盟
       // MobclickAgent.setDebugMode(true);
        mContext = getApplicationContext();
        instance = this;
        mainHanlder = new Handler();

        CretinAutoUpdateUtils.Builder builder = new CretinAutoUpdateUtils.Builder()
                .setBaseUrl("http://101.201.31.212:8016/version/checkVersion")
                .setIgnoreThisVersion(false)
                .setShowType(CretinAutoUpdateUtils.Builder.TYPE_DIALOG)
                .setIconRes(R.mipmap.ic_launcher)
                .showLog(true)
                .setRequestMethod(CretinAutoUpdateUtils.Builder.METHOD_GET)
                .build();
        CretinAutoUpdateUtils.init(builder);



        LoadingLayout.getConfig()
                .setErrorText("出错啦~请稍后重试！")
                .setEmptyText("抱歉，暂无数据")
                .setNoNetworkText("无网络连接，请检查您的网络···")
                .setErrorImage(R.mipmap.define_error)
                .setEmptyImage(R.mipmap.define_empty)
                .setNoNetworkImage(R.mipmap.define_nonetwork)
                .setAllTipTextColor(R.color.gray)
                .setAllTipTextSize(14)
                .setReloadButtonText("点我重试哦")
                .setReloadButtonTextSize(14)
                .setReloadButtonTextColor(R.color.gray)
                .setReloadButtonWidthAndHeight(150,40);
//                .setAllPageBackgroundColor(R.color.background);


    }



    public static MyApplication getInstance() {
        return instance;
    }
    public static Handler getHandler() {
        return mainHanlder;
    }

    public static int getMainThreadId() {
        return myTid;
    }

    public static Context getContext() {
        return mContext;
    }
}
