package com.zsx.rxjavatest.ui.expansion;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.view.View;

import com.zsx.rxjavatest.ui.layout.Container;

/**
 * 默认扩展视图类
 */
public class DefaultViewExpansionDelegate extends ViewExpansionDelegate {

    private ViewConfig mConfig; // 扩展视图配置
    private ProgressDialog mProgressDialog;
    private View mProgressPage;
    private View mErrorPage;

    public DefaultViewExpansionDelegate(Container container) {
        super(container);
        mConfig = getDefaultConfig();
    }

    public DefaultViewExpansionDelegate(Container container, ViewConfig config) {
        super(container);
        this.mConfig = config;
    }

    @NonNull
    private ViewConfig getDefaultConfig() {
        return new ViewConfig();
    }

    @Override
    public void showProgressDialog(String title) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        mProgressDialog = new ProgressDialog(getContentView().getContext());
        mProgressDialog.setTitle(title);
        mProgressDialog.setMessage(mConfig.mProgressMsg);
        mProgressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public View showProgressPage() {
        if (mProgressPage == null) {
            if (mConfig.mProgressPage != null) {
                mProgressPage = mConfig.mProgressPage;
            } else {
                mProgressPage = getInflater().inflate(mConfig.mProgressPageRes, getContentView(), false);
            }
        }
        if (mProgressPage.getParent() == null) {
            getContentView().addView(mProgressPage);
        }
        return mProgressPage;
    }

    @Override
    public void dismissProgressPage() {
        if (mProgressPage != null) {
            getContentView().removeView(mProgressPage);
        }
    }

    @Override
    public View showErrorPage() {
        if (mErrorPage == null) {
            if (mConfig.mErrorPage != null) {
                mErrorPage = mConfig.mErrorPage;
            } else {
                mErrorPage = getInflater().inflate(mConfig.mErrorPageRes, getContentView(), false);
            }
        }
        if (mErrorPage.getParent() == null) {
            getContentView().addView(mErrorPage);
        }
        return mErrorPage;
    }

    @Override
    public void dismissErrorPage() {
        if (mErrorPage != null) {
            getContentView().removeView(mErrorPage);
        }
    }
}
