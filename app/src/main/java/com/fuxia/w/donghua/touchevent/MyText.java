package com.fuxia.w.donghua.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

public class MyText extends TextView {
	private String TAG = "上层控件";

	public MyText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	// 如果底层空间没有拦截
	// 则优先处理上层dispatchTouchEvent事件
	public boolean dispatchTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Toast.makeText(getContext(), TAG + "dispath触发", Toast.LENGTH_SHORT)
					.show();
			Log.d(TAG, "dispatchTouch ActionDown");
			break;
		}
		return super.dispatchTouchEvent(ev);
	}

	// 如果此控件事最上层空间，则不执行此事件
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Toast.makeText(getContext(), TAG + "intercept触发",
					Toast.LENGTH_SHORT).show();
			Log.d(TAG, "intercept ActionDown");
			break;
		}
		return false;
	}

	@Override
	// 然后处理TouchEvent
	public boolean onTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Toast.makeText(getContext(), TAG + "touch触发", Toast.LENGTH_SHORT)
					.show();
			Log.d(TAG, "---onTouchEvent ACTION_DOWN");
			break;
		}
		return true;
	}
}