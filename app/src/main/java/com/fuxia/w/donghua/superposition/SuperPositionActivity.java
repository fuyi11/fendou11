package com.fuxia.w.donghua.superposition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.fuxia.w.R;


public class SuperPositionActivity extends Activity implements
		SuperPositionAdapter.LayoutPositionListener {
	private ListView list;
	private String[][] comment;
	private List<Map<String, Object>> items;

	private PopupWindow menu;
	private SuperPositionAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.listview);
		// setData();
		setComment();
	}

	private void setData() {
		Map<String, Object> map;
		items = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 10; i++) {
			map = new HashMap<String, Object>();
			map.put("lastComment", "lastcomment--" + i);
			map.put("name", "name--" + i);
			map.put("comment", "comment--" + i);
			items.add(map);
		}
		list = (ListView) findViewById(R.id.list);
		list.setAdapter(new CommentAdapter(this, items));
	}

	private void setComment() {
		comment = new String[5][2];
		for (int i = 0; i < 5; i++) {
			comment[i][0] = "name-->" + i;
			comment[i][1] = "comment-->" + i;
		}
		list = (ListView) findViewById(R.id.list);
		adapter = new SuperPositionAdapter(this, comment, 2);
		adapter.setLayoutPositionListener(this);
		list.setAdapter(adapter);
	}

	private void showMenu(View parent, int x, int y) {
		if (menu == null) {
			View view = LayoutInflater.from(this).inflate(
					R.layout.activity_touchintercept_list_item_pop, null);
			menu = new PopupWindow(view, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT, true);
			TextView replay = (TextView) view.findViewById(R.id.tv_pop_replay);
			menu.setOutsideTouchable(true);// 设置点击窗口外边窗口消失
			ColorDrawable cw = new ColorDrawable(-00000);
			menu.setBackgroundDrawable(cw);
			replay.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "reply已点击！",
							Toast.LENGTH_SHORT).show();
					menu.dismiss();
				}
			});
		}
		System.out.println("parent.scrolly=" + parent.getScrollY());
		menu.showAtLocation(parent, Gravity.TOP | Gravity.LEFT, x,
				parent.getScrollY() + y);
	}

	@Override
	public void setSuperPosition(View layout, int x, int y) {
		// TODO Auto-generated method stub
		showMenu(layout, x, y);
	}
}
