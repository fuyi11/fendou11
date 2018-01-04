package com.fuxia.w;

import android.app.Application;
import android.content.Context;
import android.os.Handler;


import com.facebook.drawee.backends.pipeline.Fresco;
import com.fuxia.w.view11.CretinAutoUpdateUtils;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
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
        Fresco.initialize(this);
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

        initImageLoader(getApplicationContext());

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

    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
