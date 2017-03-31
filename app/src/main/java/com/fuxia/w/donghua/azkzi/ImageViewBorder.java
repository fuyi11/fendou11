package com.fuxia.w.donghua.azkzi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ImageViewBorder extends ImageView {
	public ImageViewBorder(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ImageViewBorder(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ImageViewBorder(Context context) {
		super(context);
	}

	private Rect rect = new Rect();
	private Paint paint = new Paint();

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 获取控件需要重新绘制的区域
		canvas.getClipBounds(rect);
		// rect.bottom--;
		// rect.right--;
		paint.setColor(Color.parseColor("#f4f4f1"));// 边框颜色
		paint.setStyle(Paint.Style.STROKE);// 设置画笔的样式
		paint.setStrokeWidth(1);// 当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的粗细度
		canvas.drawRect(rect, paint);
	}
}
