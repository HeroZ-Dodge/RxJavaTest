package com.zsx.rxjavatest.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.zsx.rxjavatest.R;
import com.zsx.rxjavatest.ui.mvp.MvpActivity;

/**
 * Activity 抽象类
 */
public abstract class BaseActivity extends AppCompatActivity implements MvpActivity {

    private FrameLayout mFrameLayout;
    protected View mErrorView;
    protected View mEmptyView;

    protected ProgressDialog mLoadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater inflater = getLayoutInflater();
        View rootView = inflater.inflate(R.layout.base_activity, null);
        mFrameLayout = (FrameLayout) rootView.findViewById(R.id.frame_layout);
        inflater.inflate(layoutResID, mFrameLayout, true);
        super.setContentView(rootView);
        initBaseView();
    }

    private void initBaseView() {
        mErrorView = findViewById(R.id.net_error_view_stub);
        mEmptyView = findViewById(R.id.empty_view_stub);
        if (mLoadingDialog == null) {
            mLoadingDialog = new ProgressDialog(this);
            mLoadingDialog.setMessage("loading...");
        }

    }


    @Override
    public void showErrorView() {
        if (mErrorView != null) {
            mErrorView.setVisibility(View.VISIBLE);
        }
        if (mEmptyView != null) {
            mEmptyView.setVisibility(View.GONE);
        }
        if (mFrameLayout != null) {
            mFrameLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void showEmptyView() {
        if (mErrorView != null) {
            mErrorView.setVisibility(View.GONE);
        }
        if (mEmptyView != null) {
            mEmptyView.setVisibility(View.VISIBLE);
        }
        if (mFrameLayout != null) {
            mFrameLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void showContentView() {
        if (mErrorView != null) {
            mErrorView.setVisibility(View.GONE);
        }
        if (mEmptyView != null) {
            mEmptyView.setVisibility(View.GONE);
        }
        if (mFrameLayout != null) {
            mFrameLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showLoadingDialog() {
        if (mLoadingDialog != null && !mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    @Override
    public void dismissLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }
}
