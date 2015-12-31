package com.zsx.rxjavatest.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.zsx.rxjavatest.R;
import com.zsx.rxjavatest.ui.base.MvpActivity;

/**
 * Activity 抽象类
 */
public abstract class BaseActivity extends AppCompatActivity implements MvpActivity {

    private View mRootView;
    private FrameLayout mFrameLayout;
    protected View mErrorView;

    protected ProgressDialog mLoadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater inflater = getLayoutInflater();
        mRootView = inflater.inflate(R.layout.base_activity, null);
        mFrameLayout = (FrameLayout) mRootView.findViewById(R.id.frame_layout);
        inflater.inflate(layoutResID, mFrameLayout, true);
        super.setContentView(mRootView);
        initBaseView();
    }

    private void initBaseView() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new ProgressDialog(this);
            mLoadingDialog.setMessage("loading...");
        }
        mErrorView = findViewById(R.id.net_error);
    }


    @Override
    public void showErrorView() {
        if (mErrorView != null) {
            mErrorView.setVisibility(View.VISIBLE);
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
