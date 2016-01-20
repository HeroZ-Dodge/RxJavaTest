package com.zsx.rxjavatest.ui.layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.zsx.rxjavatest.ui.expansion.ViewExpansionDelegate;
import com.zsx.rxjavatest.ui.expansion.ViewExpansionDelegateProvider;

/**
 * Activity 抽象类型
 */
public class BaseActivity extends AppCompatActivity implements Container, ViewExpansionDelegate.OnRetryListener {

    private FrameLayout mContentParent;
    private ViewExpansionDelegate mViewExpansionDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preCreate();
    }

    private void preCreate() {
        mContentParent = (FrameLayout) findViewById(android.R.id.content);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mViewExpansionDelegate != null) {
            mViewExpansionDelegate.destroy();
        }
    }

    @Override
    public FrameLayout getContentView() {
        return mContentParent;
    }

    @Override
    public ViewExpansionDelegate getViewExpansionDelegate() {
        if (mViewExpansionDelegate == null) {
            mViewExpansionDelegate = ViewExpansionDelegateProvider.DEFAULT.createViewExpansionDelegate(this);
            mViewExpansionDelegate.setOnRetryListener(this);
        }
        return mViewExpansionDelegate;
    }

    @Override
    public void onRetryClick(View v) {

    }
}
