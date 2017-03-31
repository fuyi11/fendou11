package com.fuxia.w.donghua;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.Window;

public class GetValue {
	private Context context;

	GetValue(Context context) {
		this.context = context;
	}

	/**
	 * 获取状态栏高度
	 * 
	 * decorView是window中的最顶层view，getWindowVisibleDisplayFrame方法可以获取到程序显示的区域，
	 * 包括标题栏，但不包括状态栏
	 */
	public int getStatusBarHeight() {
		Rect frame = new Rect();
		((Activity) context).getWindow().getDecorView()
				.getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;
		return statusBarHeight;
	}

	/**
	 * 获取标题栏高度
	 */
	public int getTitleBarHeight() {
		int contentTop = ((Activity) context).getWindow()
				.findViewById(Window.ID_ANDROID_CONTENT).getTop();
		int titleBarHeight = contentTop - getStatusBarHeight();
		return titleBarHeight;
	}
}
