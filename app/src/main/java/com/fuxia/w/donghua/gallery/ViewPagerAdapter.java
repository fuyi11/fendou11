package com.fuxia.w.donghua.gallery;

import java.util.List;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class ViewPagerAdapter extends PagerAdapter {
	private List<View> mListViews;

	public ViewPagerAdapter(List<View> mListViews) {
		this.mListViews = mListViews;// 构造方法，参数是我们的页卡，这样比较方便。

	}

	/**
	 * 这个方法非常有用，默认返回1.0f，权重比例
	 */
	@Override
	public float getPageWidth(int position) {
		return (float) 0.3;
	}

	@Override
	public void destroyItem(View collection, int position, Object view) {
		((ViewPager) collection).removeView(mListViews.get(position));// 删除页卡
	}

	@Override
	public void finishUpdate(View arg0) {
	}

	@Override
	public int getCount() {
		return mListViews.size();// 返回页卡的数量

	}

	@Override
	public Object instantiateItem(View collection, int position) {// 这个方法用来实例化页
		((ViewPager) collection).addView(mListViews.get(position), 0);// 添加页卡
		return mListViews.get(position);
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == (object);
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {

	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	@Override
	public void startUpdate(View arg0) {
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}
}
