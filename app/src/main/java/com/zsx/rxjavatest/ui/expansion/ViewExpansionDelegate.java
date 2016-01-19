package com.zsx.rxjavatest.ui.expansion;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.zsx.rxjavatest.ui.layout.Container;

/**
 * 扩展视图抽象
 */
public abstract class ViewExpansionDelegate {

    private Container mContainer;
    private FrameLayout mContentView;
    private LayoutInflater mInflater;


    public ViewExpansionDelegate(Container container) {
        this.mContainer = container;
        this.mContentView = container.getContentView();
    }

    public final Container getContainer() {
        return mContainer;
    }

    public final LayoutInflater getInflater() {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(getContentView().getContext());
        }
        return mInflater;
    }

    public final FrameLayout getContentView() {
        return mContentView;
    }

    public abstract void showProgressDialog(String title);

    public abstract void dismissProgressDialog();

    public abstract View showProgressPage();

    public abstract void dismissProgressPage();

    public abstract View showErrorPage();

    public abstract void dismissErrorPage();

    public interface OnRetryListener {
        void onRetryClick();
    }


}
