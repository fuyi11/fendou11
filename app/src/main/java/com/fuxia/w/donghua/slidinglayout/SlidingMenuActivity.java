package com.fuxia.w.donghua.slidinglayout;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.fuxia.w.R;

public class SlidingMenuActivity extends Activity implements OnClickListener {
	private Button show;// 显示侧滑栏
	private ListView contentListView;
	/**
	 * 作用于contentListView的适配器。
	 */
	private ArrayAdapter<String> contentListAdapter;

	/**
	 * 用于填充contentListAdapter的数据源。
	 */
	private String[] contentItems = { "Content Item 1", "Content Item 2",
			"Content Item 3", "Content Item 4", "Content Item 5",
			"Content Item 6", "Content Item 7", "Content Item 8",
			"Content Item 9", "Content Item 10", "Content Item 11",
			"Content Item 12", "Content Item 13", "Content Item 14",
			"Content Item 15", "Content Item 16" };

	/**
	 * 侧滑布局对象，用于通过手指滑动将左侧的菜单布局进行显示或隐藏。
	 */
	private SlidingLayout slidingLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slidinglayout);
		show = (Button) findViewById(R.id.btn_show);
		slidingLayout = (SlidingLayout) findViewById(R.id.slidingmenu);
		// slidingLayout.leftLayoutPadding = 100;//设置左侧布局的宽度
		contentListView = (ListView) findViewById(R.id.listview);
		contentListAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, contentItems);
		contentListView.setAdapter(contentListAdapter);
		// 将监听滑动事件绑定在contentListView上
		slidingLayout.setScrollEvent(contentListView);

		show.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.btn_show:
			// 实现点击一下menu展示左侧布局，再点击一下隐藏左侧布局的功能
			if (slidingLayout.isLeftLayoutVisible()) {
				slidingLayout.scrollToRightLayout();
			} else {
				slidingLayout.scrollToLeftLayout();
			}
			break;
		}
	}
}
