package com.fuxia.w.donghua.animation;



import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fuxia.w.R;

public class SimpleAnimationActivity extends Activity implements
		OnClickListener {
	private LinearLayout contain;
	private TextView start;

	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple_animation);
		contain = (LinearLayout) findViewById(R.id.contain);
		start = (TextView) findViewById(R.id.start);
		start.setOnClickListener(this);
		contain.setAnimation(AnimationUtils.loadAnimation(this,
				R.anim.translate));
		// contain.startAnimation(AnimationUtils.loadAnimation(this,
		// R.anim.translate));
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.start:
			Animation animation = AnimationUtils.loadAnimation(this,
					R.anim.translate);
			contain.startAnimation(animation);
			// animation.setFillAfter(true);// 动画停留在最后一帧，该属性必须在java代码中设置才有效
			break;
		}
	}
}
