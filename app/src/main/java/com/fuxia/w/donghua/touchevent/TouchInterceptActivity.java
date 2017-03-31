package com.fuxia.w.donghua.touchevent;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.fuxia.w.R;


public class TouchInterceptActivity extends Activity {
	private MyLayout myLayout;
	private MyText myText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_touchintercept);

		myLayout = (MyLayout) this.findViewById(R.id.mylayout);
		myText = (MyText) this.findViewById(R.id.mytext);

	}

	@Override
	// 在活动界面里，我们可以用dispatchTouchEvent来分发触发事件
	// 通过分发来优先底层或者上层的各种事件
	public boolean dispatchTouchEvent(MotionEvent ev) {
		myLayout.onTouchEvent(ev);
		myText.onTouchEvent(ev);
		// 如果返回false 则不再执行其他任何点击事件
		// return false;
		// super.dispatchTouchEvent(ev)可以再去根据各个优先级继续处理
		// return super.dispatchTouchEvent(ev);
		// 返回 true 也将不再执行其他事件
		return true;
	}

	// activity本身的点击事件
	public boolean onTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Toast.makeText(getApplicationContext(), "执行Touch事件",
					Toast.LENGTH_SHORT).show();
			break;
		}
		return true;
	}
}
