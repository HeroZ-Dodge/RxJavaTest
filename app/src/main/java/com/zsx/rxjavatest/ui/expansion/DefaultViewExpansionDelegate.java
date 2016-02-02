package com.zsx.rxjavatest.ui.expansion;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.view.View;

import com.zsx.rxjavatest.ui.layout.ViewLayer;

/**
 * 默认扩展视图类
 */
public class DefaultViewExpansionDelegate extends ViewExpansionDelegate {

    private ViewConfig mConfig; // 扩展视图配置
    private ProgressDialog mProgressDialog; // loading 对话框
    private View mProgressPage; // loading 页面
    private View mErrorPage; // 错误页面
    private OnRetryListener mRetryListener; // 重试监听器

    public DefaultViewExpansionDelegate(ViewLayer viewLayer) {
        super(viewLayer);
        mConfig = getDefaultConfig();
    }

    public DefaultViewExpansionDelegate(ViewLayer viewLayer, ViewConfig config) {
        super(viewLayer);
        this.mConfig = config;
    }

    @NonNull
    private ViewConfig getDefaultConfig() {
        return new ViewConfig();
    }

    @Override
    public void setOnRetryListener(OnRetryListener listener) {
        this.mRetryListener = listener;
    }

    @Override
    public OnRetryListener getOnRetryListener() {
        return mRetryListener;
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
        mErrorPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissErrorPage(); // 关闭错误页面
                if (mRetryListener != null) {
                    mRetryListener.onRetryClick(v);
                }
            }
        });
        return mErrorPage;
    }

    @Override
    public void dismissErrorPage() {
        if (mErrorPage != null) {
            getContentView().removeView(mErrorPage);
        }
    }

}
