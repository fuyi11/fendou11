package com.fuxia.w.donghua.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MyLayout extends LinearLayout {
	private String TAG = "底层控件";

	public MyLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	// 优先处理底层控件的dispatchEvent 分发Touch事件
	public boolean dispatchTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Toast.makeText(getContext(), TAG + "dispatch触发", Toast.LENGTH_SHORT)
					.show();
			Log.d(TAG, "dispatchTouchEvent ACTION_DOWN");
			break;
		}
		return super.dispatchTouchEvent(ev);
	}

	// 然后处理底层空间的InterceptTouchEvent
	// 如果返回false 则跳过底层TouchEvent去处理上层控件的事件
	// 如果返回的是true 则拦截住上层所有事件，去处理底层TouchEvent
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Toast.makeText(getContext(), TAG + "intercept触发",
					Toast.LENGTH_SHORT).show();
			Log.d(TAG, "interceptTouch ActionDown");
			break;
		}
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Toast.makeText(getContext(), TAG + "touch触发", Toast.LENGTH_SHORT)
					.show();
			Log.d(TAG, "touchEvent ACTION_DOWN");
			break;
		}
		return false;
	}
}
