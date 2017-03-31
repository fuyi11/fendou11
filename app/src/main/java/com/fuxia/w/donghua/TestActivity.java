package com.fuxia.w.donghua;

import android.app.Activity;
import android.os.Bundle;

import com.fuxia.w.R;
import com.fuxia.w.donghua.waterdrop.CoverManager;


public class TestActivity extends Activity {
	// private PullListView list;
	// private ArrayAdapter<String> contentListAdapter;
	// private String[] contentItems = { "Content Item 1", "Content Item 2",
	// "Content Item 3", "Content Item 4", "Content Item 5",
	// "Content Item 6", "Content Item 7", "Content Item 8",
	// "Content Item 9", "Content Item 10", "Content Item 11",
	// "Content Item 12", "Content Item 13", "Content Item 14",
	// "Content Item 15", "Content Item 16" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.pulllistview);
		// list = (PullListView) findViewById(R.id.list);
		// contentListAdapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, contentItems);
		// list.setAdapter(contentListAdapter);
		setContentView(R.layout.waterdrop);
		CoverManager.getInstance().init(this);
		CoverManager.getInstance().setMaxDragDistance(150);
		CoverManager.getInstance().setExplosionTime(150);
	}
}
