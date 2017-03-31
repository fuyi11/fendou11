package com.fuxia.w.donghua;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.fuxia.w.R;
import com.fuxia.w.donghua.animation.SimpleAnimationActivity;
import com.fuxia.w.donghua.function.DownLoadImage;
import com.fuxia.w.donghua.gallery.GalleryPagerActivity;
import com.fuxia.w.donghua.gallery.ViewPagerActivity;
import com.fuxia.w.donghua.powerview.DragGridViewActivity;
import com.fuxia.w.donghua.powerview.PageWidgetActivity;
import com.fuxia.w.donghua.powerview.RoundImageActivity;
import com.fuxia.w.donghua.slidinglayout.SlidingMenuActivity;
import com.fuxia.w.donghua.superposition.SuperPositionActivity;
import com.fuxia.w.donghua.touchevent.TouchInterceptActivity;
import com.fuxia.w.donghua.waterdrop.test.WaterDropActivity;

import java.util.List;

/**
 * Created by fuyi on 2017/3/27.
 */

public class DongHuaActivity extends AppCompatActivity implements View.OnClickListener {
    private Button slidinglayout;
    private Button gallery;
    private Button vp_gallery;
    private Button roundimage;
    private Button simanimation;
    private Button waterdrop;
    private Button draggridview;
    private Button downimage;
    private Button turnpage;
    private Button superposition;
    private Button touchintercept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donghua);
        initView();
        initListener();
        // if (!isRunning(this)) {
        // System.out.println("启动新都旅游" + isRunning(this));
        // Intent intent = this.getPackageManager().getLaunchIntentForPackage(
        // "com.cdyztx.xdly");// packageName就是我们获得要启动应用的包名
        // startActivity(intent);
        // } else {
        // System.out.println("启动新都旅游已启动");
        // }
    }

    /**
     * @description 初始化控件
     */
    private void initView() {
        location = new int[2];
        slidinglayout = (Button) findViewById(R.id.btn_slidinglayout);
        gallery = (Button) findViewById(R.id.btn_gallery);
        vp_gallery = (Button) findViewById(R.id.vp_gallery);
        roundimage = (Button) findViewById(R.id.btn_roundimage);
        simanimation = (Button) findViewById(R.id.btn_simpleanimation);
        waterdrop = (Button) findViewById(R.id.btn_waterdrop);
        draggridview = (Button) findViewById(R.id.btn_draggridview);
        downimage = (Button) findViewById(R.id.btn_downimage);
        turnpage = (Button) findViewById(R.id.btn_turnpage);
        superposition = (Button) findViewById(R.id.btn_superposition);
        touchintercept = (Button) findViewById(R.id.btn_touchintercept);
    }

    /**
     * @description 初始化控件的点击事件
     */
    private void initListener() {
        slidinglayout.setOnClickListener(this);
        gallery.setOnClickListener(this);
        vp_gallery.setOnClickListener(this);
        roundimage.setOnClickListener(this);
        simanimation.setOnClickListener(this);
        waterdrop.setOnClickListener(this);
        draggridview.setOnClickListener(this);
        downimage.setOnClickListener(this);
        turnpage.setOnClickListener(this);
        superposition.setOnClickListener(this);
        touchintercept.setOnClickListener(this);
    }

    private int[] location;

    /**
     * @description 点击事件的动作
     */
    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
        switch (view.getId()) {
            case R.id.btn_slidinglayout:
                startActivity(new Intent(this, SlidingMenuActivity.class));
                slidinglayout.getLocationInWindow(location);
                System.out.println("该控件在当前窗口内的绝对坐标为：" + location[0] + ","
                        + location[1] + " " + slidinglayout.getHeight());
                break;
            case R.id.btn_gallery:
                startActivity(new Intent(this, GalleryPagerActivity.class));
                gallery.getLocationInWindow(location);
                System.out.println("该控件在当前窗口内的绝对坐标为：" + location[0] + ","
                        + location[1] + " " + gallery.getHeight());
                break;
            case R.id.vp_gallery:
                startActivity(new Intent(this, ViewPagerActivity.class));
                vp_gallery.getLocationInWindow(location);
                System.out.println("该控件在当前窗口内的绝对坐标为：" + location[0] + ","
                        + location[1] + " " + vp_gallery.getHeight());
                break;
            case R.id.btn_roundimage:
                startActivity(new Intent(this, RoundImageActivity.class));
                break;
            case R.id.btn_simpleanimation:
                startActivity(new Intent(this, SimpleAnimationActivity.class));
                break;
            case R.id.btn_waterdrop:
                startActivity(new Intent(this, WaterDropActivity.class));
                break;
            case R.id.btn_draggridview:
                startActivity(new Intent(this, DragGridViewActivity.class));
                break;
            case R.id.btn_downimage:
                new DownLoadImage(
                        "http://www.cdyztx.com:9090/xdslj/media/product/1396336725339.png")
                        .saveImage();
                break;
            case R.id.btn_turnpage:
                startActivity(new Intent(this, PageWidgetActivity.class));
                break;
            case R.id.btn_superposition:
                startActivity(new Intent(this, SuperPositionActivity.class));
                break;
            case R.id.btn_touchintercept:
                startActivity(new Intent(this, TouchInterceptActivity.class));
                break;
        }
    }

    /**
     *
     * add()方法的四个参数，依次是：
     *
     * 1、组别，如果不分组的话就写Menu.NONE,
     *
     * 2、Id，这个很重要，Android根据这个Id来确定不同的菜单
     *
     * 3、顺序，那个菜单现在在前面由这个参数的大小决定
     *
     * 4、文本，菜单的显示文本
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        // setIcon()方法为菜单设置图标
        menu.add(Menu.NONE, Menu.FIRST, 1, "模糊").setIcon(
                getResources().getDrawable(android.R.drawable.ic_menu_help));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case Menu.FIRST:
                // getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
                // WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                // // 填充Activity布局
                // setContentView(R.layout.activity_main);
                break;
        }
        return false;
    }

    /**
     * 判断应用在前台还是后台运行
     *
     * 需要添加android.permission.GET_TASKS权限
     *
     * @param context
     * @return
     */
    public boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                    Log.i("后台", appProcess.processName);
                    return true;
                } else {
                    Log.i("前台", appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 判断应用是否运行在最前面
     *
     * @param
     * @return
     */
    public boolean isTopActivity(Activity activity) {
        String packageName = "com.cdyztx.xdly";
        ActivityManager activityManager = (ActivityManager) activity
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
        if (tasksInfo.size() > 0) {
            System.out.println("---------------包名-----------"
                    + tasksInfo.get(0).topActivity.getPackageName());
            // 应用程序位于堆栈的顶层
            if (packageName.equals(tasksInfo.get(0).topActivity
                    .getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断应用是否正在运行
     *
     * 需要添加android.permission.GET_TASKS权限
     *
     * @param context
     * @return
     */
    public boolean isRunning(Context context) {
        // 判断应用是否在运行
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        boolean isAppRunning = false;
        String MY_PKG_NAME = "com.cdyztx.xdly";
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(MY_PKG_NAME)
                    || info.baseActivity.getPackageName().equals(MY_PKG_NAME)) {
                isAppRunning = true;
                break;
            }
        }
        return isAppRunning;
    }
}
