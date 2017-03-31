package com.fuxia.w.donghua.gallery;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.fuxia.w.R;


public class GalleryPagerActivity extends Activity {
	private static int TOTAL_COUNT = 3;

	private RelativeLayout viewPagerContainer;
	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery_viewpager);

		viewPager = (ViewPager) findViewById(R.id.view_pager);
		viewPagerContainer = (RelativeLayout) findViewById(R.id.pager_layout);
		viewPager.setAdapter(new MyPagerAdapter());
		// to cache all page, or we will see the right item delayed
		viewPager.setOffscreenPageLimit(TOTAL_COUNT);
		viewPager.setPageMargin(getResources().getDimensionPixelSize(
				R.dimen.page_margin));
		MyOnPageChangeListener myOnPageChangeListener = new MyOnPageChangeListener();
		viewPager.setOnPageChangeListener(myOnPageChangeListener);

		viewPagerContainer.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// dispatch the events to the ViewPager, to solve the problem
				// that we can swipe only the middle view.
				return viewPager.dispatchTouchEvent(event);
			}
		});
	}

	/**
	 * this is a example fragment, just a imageview, u can replace it with your
	 * needs
	 * 
	 * @author Trinea 2013-04-03
	 */
	class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return TOTAL_COUNT;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return (view == object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView imageView = new ImageView(GalleryPagerActivity.this);
			imageView.setImageResource(R.drawable.about + position);
			((ViewPager) container).addView(imageView, position);
			return imageView;

		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView((ImageView) object);
		}
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int position) {
		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			// to refresh frameLayout
			if (viewPagerContainer != null) {
				viewPagerContainer.invalidate();
			}
		}

		@Override
		public void onPageScrollStateChanged(int position) {
		}
	}
}
