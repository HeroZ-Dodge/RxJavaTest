package com.zsx.rxjavatest.ui.layout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.zsx.rxjavatest.presenter.BasePresenter;
import com.zsx.rxjavatest.ui.expansion.ViewExpansionDelegate;
import com.zsx.rxjavatest.ui.expansion.ViewExpansionDelegateProvider;

/**
 * Activity 抽象类型
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements ViewLayer, ViewExpansionDelegate.OnRetryListener {

    private FrameLayout mContentParent;
    private ViewExpansionDelegate mViewExpansionDelegate; // 扩展视图类
    private P mPresenter;

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
        if (mPresenter != null) {
            mPresenter = null;
        }
        if (mViewExpansionDelegate != null) { // 释放扩展视图资源
            mViewExpansionDelegate.destroy();
        }
    }

    @NonNull
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

    public final P getPresenter() {
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        return mPresenter;
    }

    /**
     * 创建Presenter，并与视图绑定
     *
     * @return Presenter
     */
    @NonNull
    protected abstract P createPresenter();


}
