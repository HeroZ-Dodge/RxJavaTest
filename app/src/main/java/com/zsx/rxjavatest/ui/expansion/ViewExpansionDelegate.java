package com.zsx.rxjavatest.ui.expansion;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.zsx.rxjavatest.ui.layout.ViewLayer;

/**
 * 扩展视图抽象
 */
public abstract class ViewExpansionDelegate {

    private ViewLayer mViewLayer;
    private FrameLayout mContentView;
    private LayoutInflater mInflater;

    public ViewExpansionDelegate(ViewLayer viewLayer) {
        this.mViewLayer = viewLayer;
        this.mContentView = viewLayer.getContentView();
    }

    public final ViewLayer getViewLayer() {
        return mViewLayer;
    }

    public final LayoutInflater getInflater() {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(getContentView().getContext());
        }
        return mInflater;
    }

    @NonNull
    public final FrameLayout getContentView() {
        return mContentView;
    }

    public final void destroy() {
        mViewLayer = null;
        mContentView = null;
    }

    public abstract void showProgressDialog(String title);

    public abstract void dismissProgressDialog();

    public abstract View showProgressPage();

    public abstract void dismissProgressPage();

    public abstract View showErrorPage();

    public abstract void dismissErrorPage();

    public abstract void setOnRetryListener(OnRetryListener listener);

    public abstract OnRetryListener getOnRetryListener();

    public interface OnRetryListener { // 网络异常时，点击重试监听
        void onRetryClick(View v);
    }


}
