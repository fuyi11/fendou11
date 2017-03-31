package com.fuxia.w.donghua;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * 可拖拽的控件
 * 
 * @author Administrator
 * 
 */
public class HaulView implements OnTouchListener {
	private int lastX;
	private int lastY;

	private int screenWidth;
	private int screenHeight;

	public HaulView(Context context, View view) {
		DisplayMetrics dm = ((Activity) context).getResources()
				.getDisplayMetrics();
		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels;
		view.setOnTouchListener(this);// ImageView和TextView这些控件，要想实现拖动，要在xml文件中设置它的clickable为true
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			lastX = (int) event.getRawX();
			lastY = (int) event.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			int dx = (int) event.getRawX() - lastX;
			int dy = (int) event.getRawY() - lastY;

			int left = view.getLeft() + dx;
			int top = view.getTop() + dy;
			int right = view.getRight() + dx;
			int bottom = view.getBottom() + dy;
			if (left < 0) {
				left = 0;
				right = left + view.getWidth();
			}
			if (right > screenWidth) {
				right = screenWidth;
				left = right - view.getWidth();
			}
			if (top < 0) {
				top = 0;
				bottom = top + view.getHeight();
			}
			if (bottom > screenHeight) {
				bottom = screenHeight;
				top = bottom - view.getHeight();
			}
			view.layout(left, top, right, bottom);
			lastX = (int) event.getRawX();// 注意getRawX()和getX()的区别
			lastY = (int) event.getRawY();
			break;
		case MotionEvent.ACTION_UP:
			break;
		}
		return false;
	}

}
