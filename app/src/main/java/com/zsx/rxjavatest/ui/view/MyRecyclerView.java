package com.zsx.rxjavatest.ui.view;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zsx.rxjavatest.R;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/1/15.
 */
public class MyRecyclerView extends RecyclerView {

    public final static int LOADING_STATUS_DEFAULT = -1;
    public final static int LOADING_STATUS_WAIT = 0;
    public final static int LOADING_STATUS_START = 1;
    public final static int LOADING_STATUS_FINISH = 2;
    private int mLoadStatus = LOADING_STATUS_WAIT;
    private View mLoadMoreView;
    private TextView mTvFootMsg;
    private ProgressBar mProgressBar;

    private final String NOT_MORE = "没有更多...";

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) getLayoutManager();
            System.out.println(getScrolledDistance());
            if (layoutManager != null && (mLoadStatus == LOADING_STATUS_WAIT || mLoadStatus == LOADING_STATUS_DEFAULT)) {
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                int itemCount = layoutManager.getItemCount();
                int childCount = layoutManager.getChildCount();
                if (childCount > 0 && (lastVisibleItemPosition >= itemCount - 2)) {
                    System.out.println("loading start");
                    setLoadStatus(LOADING_STATUS_START);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("loading finish");
                            setLoadStatus(LOADING_STATUS_WAIT);
                        }
                    }, 3000);
                }
            }
        }
    }

    private ArrayList<View> mHeaderViews = new ArrayList<>();

    private ArrayList<View> mFootViews = new ArrayList<>();

    private Adapter mAdapter;

    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addHeaderView(View view) {
        mHeaderViews.clear();
        mHeaderViews.add(view);
        if (mAdapter != null) {
            if (!(mAdapter instanceof RecyclerWrapAdapter)) {
                mAdapter = new RecyclerWrapAdapter(mHeaderViews, mFootViews, mAdapter);
//                mAdapter.notifyDataSetChanged();
            }
        }
    }

    public void addFootView(View view) {
        mFootViews.clear();
        mFootViews.add(view);
        if (mAdapter != null) {
            if (!(mAdapter instanceof RecyclerWrapAdapter)) {
                mAdapter = new RecyclerWrapAdapter(mHeaderViews, mFootViews, mAdapter);
                mAdapter.notifyDataSetChanged();
            }
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (mHeaderViews.isEmpty() && mFootViews.isEmpty()) {
            super.setAdapter(adapter);
        } else {
            adapter = new RecyclerWrapAdapter(mHeaderViews, mFootViews, adapter);
            super.setAdapter(adapter);
        }
        mAdapter = adapter;
    }

    public TextView getTvFootMsg() {
        if (mTvFootMsg == null) {
            if (mFootViews != null) {
                mTvFootMsg = (TextView) mFootViews.get(0).findViewById(R.id.text_view);
            }
        }
        return mTvFootMsg;
    }


    public ProgressBar getFootProgress() {
        if (mProgressBar == null) {
            if (mFootViews != null && mFootViews.size() > 0) {
                mProgressBar = (ProgressBar) mFootViews.get(0).findViewById(R.id.progress_bar);
            }
        }
        return mProgressBar;
    }


    public void setLoadStatus(int status) {
        mLoadStatus = status;
        switch (status) {
            case LOADING_STATUS_DEFAULT:
                getFootProgress().setVisibility(GONE);
                getTvFootMsg().setVisibility(GONE);
                break;

            case LOADING_STATUS_WAIT:
                getFootProgress().setVisibility(VISIBLE);
                getTvFootMsg().setVisibility(GONE);
                break;

            case LOADING_STATUS_START:
                getFootProgress().setVisibility(VISIBLE);
                getTvFootMsg().setVisibility(GONE);
                break;

            case LOADING_STATUS_FINISH:
                getFootProgress().setVisibility(GONE);
                getTvFootMsg().setVisibility(VISIBLE);
                getTvFootMsg().setText("最底部");
                break;

            default:
                break;
        }
    }

    /**
     * 获取滚动距离
     * @return
     */
    private int getScrolledDistance() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) getLayoutManager();
        int firstItemPosition = layoutManager.findFirstVisibleItemPosition();
        View firstVisibleItem = layoutManager.findViewByPosition(firstItemPosition);
//        View view = layoutManager.findViewByPosition(0);
//        System.out.println(view.getTop());
//        System.out.println(view.getScrollY());
        firstVisibleItem.getTop();
        int itemHeight = firstVisibleItem.getHeight();
        int firstItemBottom = layoutManager.getDecoratedBottom(firstVisibleItem);
        return (firstItemPosition + 1) * itemHeight - firstItemBottom;

    }

}
