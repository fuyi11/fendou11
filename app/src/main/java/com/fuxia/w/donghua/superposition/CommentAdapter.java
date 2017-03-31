package com.fuxia.w.donghua.superposition;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fuxia.w.R;


public class CommentAdapter extends BaseAdapter {
	private Context context;
	private List<Map<String, Object>> mAryList;
	private int[] color = new int[] { Color.CYAN, Color.RED, Color.BLUE,
			Color.BLACK, Color.DKGRAY, Color.GREEN, Color.LTGRAY,
			Color.MAGENTA, Color.WHITE, Color.YELLOW };
	private int pad = 2;

	public CommentAdapter(Context context, List<Map<String, Object>> strings) {
		this.context = context;
		this.mAryList = strings;
	}

	@Override
	public int getCount() {
		return 1;
	}

	@Override
	public Object getItem(int position) {
		return mAryList != null ? mAryList.get(position) : null;
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
		holder.lastComment.setText(mAryList.get(position).get("lastComment")
				.toString());
		String[] strs = new String[mAryList.size()];
		String[] strs1 = new String[mAryList.size()];
		for (int i = 0; i < mAryList.size(); i++) {
			strs[i] = (String) mAryList.get(i).get("name");
			strs1[i] = (String) mAryList.get(i).get("comment");
		}
		holder.layout.addView(add(context, (strs.length - 1), pad, strs, strs1,
				color));
		convertView.setBackgroundColor(Color.TRANSPARENT);
		return convertView;
	}

	/**
	 * 递归加载楼层的方法
	 * 
	 * @param context上下文的对像
	 * @param 递归的控制参数
	 *            ，同时也是取用户评论信息和背景色的下标，引参数的大小必须是从集合中获得的用户名数组或从集合中获得的评论内容数据的大小减一
	 * @param pad
	 *            楼层的间距
	 * @param strs
	 *            用户名的字符串数组
	 * @param strs1
	 *            用户相应评论内容的字符串数组
	 * @param color
	 *            背景色的数组,实际应用的时候这个参数可以不用，用一张背景图片代替就行
	 * @return 返回一个楼层的LinearLayout布局对象
	 */
	private LinearLayout add(Context context, int i, int pad, String[] strs,
			String[] strs1, int[] color) {
		LinearLayout layout1 = (LinearLayout) LayoutInflater.from(context)
				.inflate(R.layout.add, null);
		TextView name = (TextView) layout1.findViewById(R.id.add_textView01);
		TextView page = (TextView) layout1.findViewById(R.id.add_textView02);
		TextView comment = (TextView) layout1.findViewById(R.id.add_textView03);
		name.setText(strs[i]);
		page.setText((i + 1) + "");
		comment.setText(strs1[i]);
		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setBackgroundColor(color[i]);
		layout.setPadding(pad, pad, pad, pad);
		if (i != 0) {
			layout.addView(add(context, --i, pad, strs, strs1, color));
		}
		layout.addView(layout1);
		return layout;
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
		layout.setPadding(interval, interval, interval, interval);
		if (i != 0) {
			layout.addView(add(context, --i, pad, names, content));
		}
		layout.addView(layout1);
		return layout;
	}

	private class ViewHolder {
		LinearLayout layout;
		TextView lastComment;
	}
}
