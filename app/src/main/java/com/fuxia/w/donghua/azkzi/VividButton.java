package com.fuxia.w.donghua.azkzi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.fuxia.w.R;


/**
 * 扁平的动态按钮
 */
public class VividButton extends Button implements View.OnTouchListener {

	// Custom values
	private boolean isShadowEnabled = true;
	private int mButtonColor;
	private int mShadowColor;
	private int mShadowHeight;
	private int mCornerRadius;
	// Native values
	private int mPaddingLeft;
	private int mPaddingRight;
	private int mPaddingTop;
	private int mPaddingBottom;

	boolean isShadowColorDefined = false;

	public VividButton(Context context) {
		super(context);
		init();
		this.setOnTouchListener(this);
	}

	public VividButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		parseAttrs(context, attrs);
		this.setOnTouchListener(this);
	}

	public VividButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
		parseAttrs(context, attrs);
		this.setOnTouchListener(this);
	}

	/**
	 * @description 当View和它的所有子对象从XML中导入之后，调用此方法
	 */
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		// Update background color
		refresh();
	}

	@Override
	public boolean onTouch(View view, MotionEvent motionEvent) {
		switch (motionEvent.getAction()) {
		case MotionEvent.ACTION_DOWN:
			this.setPadding(mPaddingLeft, mPaddingTop + mShadowHeight,
					mPaddingRight, mPaddingBottom);
			break;
		case MotionEvent.ACTION_MOVE:// 触点坐标移动到View外时，View恢复至未按下时的状态
			Rect r = new Rect();
			view.getLocalVisibleRect(r);
			if (!r.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
				this.setPadding(mPaddingLeft, mPaddingTop + mShadowHeight,
						mPaddingRight, mPaddingBottom + mShadowHeight);
			}
			break;
		case MotionEvent.ACTION_OUTSIDE:
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			this.setPadding(mPaddingLeft, mPaddingTop + mShadowHeight,
					mPaddingRight, mPaddingBottom + mShadowHeight);
			break;
		}
		return false;
	}

	private void init() {
		// Init default values
		isShadowEnabled = true;
		Resources resources = getResources();
		if (resources == null)
			return;
		mButtonColor = resources.getColor(R.color.vividbutton_default_color);
		mShadowColor = resources
				.getColor(R.color.vividbutton_default_shadow_color);
		mShadowHeight = resources
				.getDimensionPixelSize(R.dimen.vividbutton_default_shadow_height);
		mCornerRadius = resources
				.getDimensionPixelSize(R.dimen.vividbutton_default_conner_radius);
	}

	private void parseAttrs(Context context, AttributeSet attrs) {
		// TypedArray实例是个属性的容器，context.obtainStyledAttributes()方法返回得到，AttributeSet是节点的属性集合
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.vivid_button);
		if (typedArray == null)
			return;
		for (int i = 0; i < typedArray.getIndexCount(); i++) {
			int attr = typedArray.getIndex(i);
			if (attr == R.styleable.vivid_button_shadow_enabled) {
				isShadowEnabled = typedArray.getBoolean(attr, true); // Default
																		// is
																		// true
			} else if (attr == R.styleable.vivid_button_button_color) {
				mButtonColor = typedArray.getColor(attr,
						R.color.vividbutton_default_color);
			} else if (attr == R.styleable.vivid_button_shadow_color) {
				mShadowColor = typedArray.getColor(attr,
						R.color.vividbutton_default_shadow_color);
				isShadowColorDefined = true;
			} else if (attr == R.styleable.vivid_button_shadow_height) {
				mShadowHeight = typedArray.getDimensionPixelSize(attr,
						R.dimen.vividbutton_default_shadow_height);
			} else if (attr == R.styleable.vivid_button_corner_radius) {
				mCornerRadius = typedArray.getDimensionPixelSize(attr,
						R.dimen.vividbutton_default_conner_radius);
			}
		}
		typedArray.recycle();

		// Get paddingLeft, paddingRight
		int[] attrsArray = new int[] { android.R.attr.paddingLeft, // 0
				android.R.attr.paddingRight, // 1
		};
		TypedArray ta = context.obtainStyledAttributes(attrs, attrsArray);
		if (ta == null)
			return;
		mPaddingLeft = ta.getDimensionPixelSize(0, 0);
		mPaddingRight = ta.getDimensionPixelSize(1, 0);
		ta.recycle();

		// Get paddingTop, paddingBottom
		int[] attrsArray2 = new int[] { android.R.attr.paddingTop, // 0
				android.R.attr.paddingBottom,// 1
		};
		TypedArray ta1 = context.obtainStyledAttributes(attrs, attrsArray2);
		if (ta1 == null)
			return;
		mPaddingTop = ta1.getDimensionPixelSize(0, 0);
		mPaddingBottom = ta1.getDimensionPixelSize(1, 0);
		ta1.recycle();
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public void refresh() {
		float[] hsv = new float[3];
		Color.colorToHSV(mButtonColor, hsv);// 将ARGB转化为HSV（H色调，S饱和度，V亮度）
		hsv[2] *= 0.8f; // Value component（亮度改为原来的80%）
		// if shadow color was not defined, generate shadow color = 80%
		// brightness
		if (!isShadowColorDefined) {
			mShadowColor = Color.HSVToColor(hsv);// 将HSV转化为ARGB值
		}

		StateListDrawable stateListDrawable = new StateListDrawable();
		if (isShadowEnabled) {
			// Shadow is enabled
			stateListDrawable.addState(
					new int[] { -android.R.attr.state_pressed },
					createDrawable(mCornerRadius, mButtonColor, mShadowColor));
			stateListDrawable.addState(
					new int[] { android.R.attr.state_pressed },
					createDrawable(mCornerRadius, Color.TRANSPARENT,
							mButtonColor));
		} else {
			// Shadow is disabled
			mShadowHeight = 0;
			stateListDrawable.addState(
					new int[] { -android.R.attr.state_pressed },
					createDrawable(mCornerRadius, mButtonColor,
							Color.TRANSPARENT));
			stateListDrawable.addState(
					new int[] { android.R.attr.state_pressed },
					createDrawable(mCornerRadius, mShadowColor,
							Color.TRANSPARENT));
		}

		// Set button background
		if (Build.VERSION.SDK_INT >= 16) {
			this.setBackground(stateListDrawable);
		} else {
			this.setBackgroundDrawable(stateListDrawable);
		}

		// Set padding
		this.setPadding(mPaddingLeft, mPaddingTop + mShadowHeight,
				mPaddingRight, mPaddingBottom + mShadowHeight);
	}

	private LayerDrawable createDrawable(int radius, int topColor,
			int bottomColor) {

		float[] outerRadius = new float[] { radius, radius, radius, radius,
				radius, radius, radius, radius };

		// Top
		RoundRectShape topRoundRect = new RoundRectShape(outerRadius, null,
				null);
		ShapeDrawable topShapeDrawable = new ShapeDrawable(topRoundRect);
		topShapeDrawable.getPaint().setColor(topColor);
		// Bottom
		RoundRectShape roundRectShape = new RoundRectShape(outerRadius, null,
				null);
		ShapeDrawable bottomShapeDrawable = new ShapeDrawable(roundRectShape);
		bottomShapeDrawable.getPaint().setColor(bottomColor);
		// Create array
		Drawable[] drawArray = { bottomShapeDrawable, topShapeDrawable };
		LayerDrawable layerDrawable = new LayerDrawable(drawArray);

		// Set shadow height
		if (isShadowEnabled && topColor != Color.TRANSPARENT) {
			// unpressed drawable
			layerDrawable.setLayerInset(0, 0, 0, 0, 0); /*
														 * index, left, top,
														 * right, bottom
														 */
		} else {
			// pressed drawable
			layerDrawable.setLayerInset(0, 0, mShadowHeight, 0, 0); /*
																	 * index,
																	 * left,
																	 * top,
																	 * right,
																	 * bottom
																	 */
		}
		layerDrawable.setLayerInset(1, 0, 0, 0, mShadowHeight); /*
																 * index, left,
																 * top, right,
																 * bottom
																 */

		return layerDrawable;
	}

	// Setter
	public void setShadowEnabled(boolean isShadowEnabled) {
		this.isShadowEnabled = isShadowEnabled;
		setShadowHeight(0);
		refresh();
	}

	public void setButtonColor(int buttonColor) {
		this.mButtonColor = buttonColor;
		refresh();
	}

	public void setShadowColor(int shadowColor) {
		this.mShadowColor = shadowColor;
		isShadowColorDefined = true;
		refresh();
	}

	public void setShadowHeight(int shadowHeight) {
		this.mShadowHeight = shadowHeight;
		refresh();
	}

	public void setCornerRadius(int cornerRadius) {
		this.mCornerRadius = cornerRadius;
		refresh();
	}

	public void setFButtonPadding(int left, int top, int right, int bottom) {
		mPaddingLeft = left;
		mPaddingRight = right;
		mPaddingTop = top;
		mPaddingBottom = bottom;
		refresh();
	}

	// Getter
	public boolean isShadowEnabled() {
		return isShadowEnabled;
	}

	public int getButtonColor() {
		return mButtonColor;
	}

	public int getShadowColor() {
		return mShadowColor;
	}

	public int getShadowHeight() {
		return mShadowHeight;
	}

	public int getCornerRadius() {
		return mCornerRadius;
	}
}
