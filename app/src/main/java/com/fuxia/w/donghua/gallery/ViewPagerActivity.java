package com.fuxia.w.donghua.gallery;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.fuxia.w.R;

public class ViewPagerActivity extends Activity {
	private ViewPager viewpager;
	private List<View> list;

	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager);
		viewpager = (ViewPager) findViewById(R.id.pager);
		viewpager.setPageMargin(getResources().getDimensionPixelSize(
				R.dimen.page_margin));
		list = new ArrayList<View>();
		init();
	};

	private void init() {
		for (int i = 0; i < 3; i++) {
			ImageView img = (ImageView) LayoutInflater.from(this)
					.inflate(R.layout.imageview, null)
					.findViewById(R.id.imageview);
			img.setImageResource(R.drawable.about);
			list.add(img);
		}
		viewpager.setAdapter(new ViewPagerAdapter(list));
	}
}
