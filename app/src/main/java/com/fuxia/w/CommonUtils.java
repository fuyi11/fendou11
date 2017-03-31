package com.fuxia.w;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.concurrent.atomic.AtomicInteger;


public class CommonUtils {

    /**
     * xml布局 转成 View对象
     * @param id
     * @return
     */
    public static View inflate(int id){
//		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		LayoutInflater.from(getContext()).inflate(id, null);

        return View.inflate(getContext(), id, null);
    }


    /**
     * get screen height of this cellphone
     *
     * @param context
     * @return
     */
    public static int getMobileHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels; // 得到高度
        return height;
    }
    /**
     * dip 转 px
     * px = dip * density
     * @param dip
     * @return
     */
    public static int dip2px(int dip){
        float density = getContext().getResources().getDisplayMetrics().density;
        // float -> int  1.1 1  1.6 1
        //				 1.6 1  2.1 2
        return (int) (dip * density + 0.5f);
    }

    public static int px2dip(int px){
        float density = getContext().getResources().getDisplayMetrics().density;
        // float -> int  1.1 1  1.6 1
        //				 1.6 1  2.1 2
        return (int) (px / density + 0.5f);
    }

    /**
     * 运行一个任务在主线程
     */
    public static void runInMainThread(Runnable task){
        //判断当前线程是否是主线程
        if(isMainThread()){
            //当前是主线程
            task.run();
        }else{
            Handler handler = MyApplication.getHandler();
            handler.post(task);
        }
    }

    /**
     * 判断当前线程是否是主线程
     * @return
     */
    public static boolean isMainThread(){
//		if(android.os.Process.myTid() == MyApplication.getMainThreadId()){
//			return true;
//		}else{
//			return false;
//		}

        return android.os.Process.myTid() == MyApplication.getMainThreadId();
    }


    public static Context getContext(){
        return MyApplication.getContext();
    }

    /**
     * 把自身从父控件中移除
     *
     */
    public static void removeFromParent(View child) {
        if(child != null){
            ViewParent parent = child.getParent();
            if(parent != null && parent instanceof ViewGroup){
                ViewGroup viewGroup = (ViewGroup) parent;
                viewGroup.removeView(child);
            }
        }
    }

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);


    public static int generateViewId() {
        for (;;) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    /**
     * 创建一个圆角矩形
     * @param color
     * @param radius
     * @return
     */
    public static GradientDrawable getGradientDrawable(int color, int radius){
        //圆角矩形
        GradientDrawable gradientDrawable = new GradientDrawable();
        //设置类型
        gradientDrawable.setGradientType(GradientDrawable.RECTANGLE);
        //设置颜色
        gradientDrawable.setColor(color);
        //设置圆角
        gradientDrawable.setCornerRadius(radius);
        //设置矩形边缘大小和颜色
        gradientDrawable.setStroke(1, color);

        return gradientDrawable;
    }

    public static StateListDrawable getStateListDrawable(Drawable pressedDrawable, Drawable normalDrawable){
        //背景颜色选择器
        StateListDrawable stateListDrawable = new StateListDrawable();
        //添加状态和对应的Drawable
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
        stateListDrawable.addState(new int[]{}, normalDrawable);

        return stateListDrawable;
    }



    public static Drawable getDrawble(Context conetxt, @DrawableRes int id){
        return ContextCompat.getDrawable(conetxt,id);
    }

    public static int getColor(Context conetxt,@ColorRes int id){
        return  ContextCompat.getColor(conetxt,id);
    }

    public static String getString(Context conetxt,@StringRes int id){
        return  conetxt.getResources().getString(id);
    }

    public static int sp2px(Context context,float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics()
                .scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int dp2px(Context context,int dip) {
        final float scale =  context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    public static <T extends View> T findViewById(View v, int id) {


        return (T) v.findViewById(id);
    }
}
