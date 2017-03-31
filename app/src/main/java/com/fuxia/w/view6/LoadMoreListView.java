package com.fuxia.w.view6;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by fuyi on 2017/1/3.
 */

public class LoadMoreListView extends ListView {
    private OnLoadMoreListener mOnLoadMoreListener;
    private Footer mFooter;
    private boolean isEnd = false;

    public LoadMoreListView(Context context) {
        super(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        setOnScrollListener(new OnScrollListener() {
            private int lastItemIndex = Integer.MIN_VALUE;// 当前ListView中最后一个Item的索引

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // &&mFooter!=null&&mFooter.footView!=null&&mFooter.footView.hasWindowFocus()
                // KLog.debug("getItemCount():" + getItemCount());
                // KLog.debug("lastItemIndex:" + lastItemIndex);
                // KLog.debug("getFooterViewsCount():"
                // + getFooterViewsCount());
                if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
                        && lastItemIndex == getItemCount() - 1
                        && getFooterViewsCount() != 0) {
                    synchronized (mOnLoadMoreListener) {
                        if (mOnLoadMoreListener != null) {
                            showLoading();
                            mOnLoadMoreListener.onLoad();
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                lastItemIndex = firstVisibleItem + visibleItemCount - 1
                        - getFooterViewsCount() - getHeaderViewsCount();
                if (getFooterViewsCount() != 0) {
                    int visibleListItemCount = visibleItemCount
                            - getFooterViewsCount();

                    if ((visibleListItemCount == 0 && isFooterViewVisible())
                            || (visibleListItemCount >= getItemCount()
                            && isLastItemVisible() && isFirstItemVisible())) {
                        lastItemIndex = Integer.MIN_VALUE;
                        removeFooterView(mFooter.footView);
                    }
                } else {
                    if (visibleItemCount != 0 && visibleItemCount <= getCount()
                            && !isLastItemVisible()) {
                        addFooterView(mFooter.footView);
                    }
                }

                // 判断footerview是否显示
                if (!isFooterViewVisible()) {
                    if (isEnd) {
                        showEnd();
                    } else {
                        showNormal();
                    }
                }
            }
        });
    }

    /**
     * @param @param v
     * @return void
     * @throws
     * @Description: 设置FooterView
     * @author kesar
     * @date 2015-11-16
     */
    public void setFooterView(View v) {
        addFooterView(v);
        if (mFooter == null) {
            mFooter = new Footer();
        }
        mFooter.footView = v;
        ViewGroup group = (ViewGroup) v;
        for (int i = 0, count = group.getChildCount(); i < count; i++) {
            View view = group.getChildAt(i);
            if (view instanceof ProgressBar) {
                mFooter.progressBar = (ProgressBar) view;
            } else if (view instanceof TextView) {
                mFooter.textView = (TextView) view;
            }
        }
    }

    /**
     * @param @param onLoadMoreListener
     * @return void
     * @throws
     * @Description: 设置加载更多监听器
     * @author kesar
     * @date 2015-11-14
     */
    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.mOnLoadMoreListener = onLoadMoreListener;
    }

    /**
     * ClassName: OnLoadMoreListener
     *
     * @author kesar
     * @Description: 加载更多地监听器
     * @date 2015-11-14
     */
    public interface OnLoadMoreListener {
        public void onLoad();
    }

    /**
     * @param @return true完全显示出来，否则false
     * @return boolean
     * @throws
     * @Description: 判断最后listView中最后一个item(除了footview)是否完全显示出来 listView
     * 是集合的那个ListView
     * @author kesar
     * @date 2015-11-16
     */
    protected boolean isLastItemVisible() {
        if (getChildCount() == 0) {
            return true;
        }
        View lastVisibleChild = this.getChildAt(getChildCount() - 1);
        if (lastVisibleChild == null) {
            return true;
        }
        if (lastVisibleChild.equals(mFooter.footView)) {
            lastVisibleChild = this.getChildAt(getChildCount() - 2);
        }
        return lastVisibleChild.getBottom() <= this.getBottom();
    }

    /**
     * @param @return true完全显示出来，否则false
     * @return boolean
     * @throws
     * @Description: 判断第一个item是否完全显示
     * @author kesar
     * @date 2015-11-16
     */
    protected boolean isFirstItemVisible() {
        if (getChildCount() == 0) {
            return true;
        }
        View firstVisibleChild = this.getChildAt(0);
        return firstVisibleChild == null ? true
                : firstVisibleChild.getTop() >= this.getTop();
    }

    /**
     * @param @return
     * @return boolean
     * @throws
     * @Description: 判断是否footerview是否显示
     * @author kesar
     * @date 2015-11-16
     */
    protected boolean isFooterViewVisible() {
        if (getFooterViewsCount() == 0 || mFooter == null
                || mFooter.footView == null || mFooter.footView.getTop() == 0) {
            return false;
        } else {
            return mFooter.footView.getTop() <= this.getBottom();
        }
    }

    /**
     * @param @return
     * @return int
     * @throws
     * @Description: 得到item的数量(footer和header除外的数量)
     * @author kesar
     * @date 2015-11-16
     */
    protected int getItemCount() {
        return getCount() - getFooterViewsCount() - getHeaderViewsCount();
    }

    /**
     * @param @param isEnd
     * @return void
     * @throws
     * @Description: 是否到底
     * @author kesar
     * @date 2015-11-16
     */
    public void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
        if (isEnd) {
            showEnd();
        } else {
            showNormal();
        }
    }

    /**
     * @param
     * @return void
     * @throws
     * @Description: 显示正在加载
     * @author kesar
     * @date 2015-11-16
     */
    protected void showLoading() {
        if (mFooter != null && mFooter.state != Footer.STATE_LOADING) {
            mFooter.progressBar.setVisibility(View.VISIBLE);
            mFooter.textView.setText("正在加载...");
            mFooter.state = Footer.STATE_LOADING;
        }
    }

    /**
     * @param
     * @return void
     * @throws
     * @Description: 显示正常的状态
     * @author kesar
     * @date 2015-11-16
     */
    protected void showNormal() {
        if (mFooter != null && mFooter.state != Footer.STATE_NORMAL) {
            mFooter.progressBar.setVisibility(View.GONE);
            mFooter.textView.setText("加载更多");
            mFooter.state = Footer.STATE_NORMAL;
        }
    }

    /**
     * @param
     * @return void
     * @throws
     * @Description: 显示已到底
     * @author kesar
     * @date 2015-11-16
     */
    protected void showEnd() {
        if (mFooter != null && mFooter.state != Footer.STATE_END) {
            mFooter.progressBar.setVisibility(View.GONE);
            mFooter.textView.setText("已加载全部");
            mFooter.state = Footer.STATE_END;
        }
    }
}

/**
 * ClassName: Footer
 *
 * @author kesar
 * @Description: 将页脚的view封装在一起
 * @date 2015-11-16
 */
class Footer {
    public static final int STATE_NORMAL = 0x1; // 默认状态
    public static final int STATE_LOADING = 0x2; // 正在加载
    public static final int STATE_END = 0x3; // 已加载全部

    View footView; // 整个大view
    ProgressBar progressBar; // 加载view
    TextView textView; // 信息view
    int state = 0x1; // footer的状态
}
