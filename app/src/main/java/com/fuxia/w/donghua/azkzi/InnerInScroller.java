package com.fuxia.w.donghua.azkzi;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 用于在ScrollView内嵌套ListView只显示一行的解决情况
 * 
 * @author Administrator
 * 
 */
public class InnerInScroller extends ListView {
	public InnerInScroller(Context context) {
		// TODO Auto-generated method stub
		super(context);
	}

	public InnerInScroller(Context context, AttributeSet attrs) {
		// TODO Auto-generated method stub
		super(context, attrs);
	}

	public InnerInScroller(Context context, AttributeSet attrs, int defStyle) {
		// TODO Auto-generated method stub
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
