package com.fuxia.w.donghua.superposition;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.fuxia.w.R;


public class SuperPositionAdapter extends BaseAdapter {
	private Context context;
	private String[][] mAryList;
	private int padding = 5;
	private int size;
	private int[] parentPositon = new int[2];// listview的每条item的左上角的坐标

	public SuperPositionAdapter(Context context, String[][] content, int size) {
		this.context = context;
		this.mAryList = content;
		this.size = size;
	}

	/**
	 * 
	 * @param context
	 * @param
	 * @param size
	 *            列表的长度
	 * @param padding
	 *            每层之间的间距
	 */
	public SuperPositionAdapter(Context context, String[][] content, int size,
			int padding) {
		this.context = context;
		this.mAryList = content;
		this.size = size;
		this.padding = padding;
	}

	@Override
	public int getCount() {
		return size;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.add_contain, null);
			convertView.getLocationInWindow(parentPositon);
			holder.layout = (LinearLayout) convertView
					.findViewById(R.id.main_linearLayout);
			holder.lastComment = (TextView) convertView
					.findViewById(R.id.main_textView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
			// 此处清除子View
			holder.layout.removeAllViews();
		}
		holder.lastComment.setText(mAryList[mAryList.length - 1][1]);
		String[] names = new String[mAryList.length];
		String[] content = new String[mAryList.length];
		for (int i = 0; i < mAryList.length; i++) {
			names[i] = (String) mAryList[i][0];
			content[i] = (String) mAryList[i][1];
		}
		holder.layout.addView(add(context, (names.length - 1), padding, names,
				content));
		convertView.setBackgroundColor(Color.TRANSPARENT);
		return convertView;
	}

	/**
	 * @description 递归加载楼层的方法
	 * 
	 * @param context上下文的对像
	 * @param i
	 *            递归的控制参数
	 *            ，同时也是取用户评论信息和背景色的下标，引参数的大小必须是从集合中获得的用户名数组或从集合中获得的评论内容数据的大小减一
	 * @param interval
	 *            楼层的间距
	 * @param names
	 *            用户名的字符串数组
	 * @param content
	 *            用户相应评论内容的字符串数组
	 * @return 返回一个楼层的LinearLayout布局对象
	 */
	private LinearLayout add(Context context, int i, int interval,
			String[] names, String[] content) {
		LinearLayout layout1 = (LinearLayout) LayoutInflater.from(context)
				.inflate(R.layout.add, null);
		TextView name = (TextView) layout1.findViewById(R.id.add_textView01);
		TextView page = (TextView) layout1.findViewById(R.id.add_textView02);
		TextView comment = (TextView) layout1.findViewById(R.id.add_textView03);
		name.setText(names[i]);
		page.setText((i + 1) + "");
		comment.setText(content[i]);
		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setBackgroundResource(R.drawable.superposition_bg);
		layout.setPadding(interval, interval, interval, interval);
		if (i != 0) {
			layout.addView(add(context, --i, padding, names, content));
		}
		layout.addView(layout1);
		layout.setOnTouchListener(touch);
		layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				v.getLocationInWindow(parentPositon);
				System.out.println("position.x=" + parentPositon[0]
						+ ",position.y=" + parentPositon[1] + ",x=" + x + ",y="
						+ y);
				// showMenu(v, x, y + parentPositon[1]);
				if (listener != null) {
					listener.setSuperPosition(v, x, parentPositon[1] + y);
				}
			}
		});
		return layout;
	}

	private class ViewHolder {
		LinearLayout layout;
		TextView lastComment;
	}

	private int x;
	private int y;
	private OnTouchListener touch = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				x = (int) event.getX();
				y = (int) event.getY();
				break;
			}
			return false;
		}
	};

	private PopupWindow menu;

	private void showMenu(View parent, int x, int y) {
		if (menu == null) {
			View view = LayoutInflater.from(context).inflate(
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
					Toast.makeText(context, "reply已点击！", Toast.LENGTH_SHORT)
							.show();
					menu.dismiss();
				}
			});
		}
		menu.showAtLocation(parent, Gravity.TOP | Gravity.LEFT, x, y);
	}

	private LayoutPositionListener listener;

	public interface LayoutPositionListener {
		public void setSuperPosition(View layout, int x, int y);// 设置点击的楼层的事件
	}

	public void setLayoutPositionListener(LayoutPositionListener listener) {
		this.listener = listener;
	}
}
