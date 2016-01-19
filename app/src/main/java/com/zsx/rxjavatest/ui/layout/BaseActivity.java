package com.zsx.rxjavatest.ui.layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.zsx.rxjavatest.ui.expansion.ViewExpansionDelegate;
import com.zsx.rxjavatest.ui.expansion.ViewExpansionDelegateProvider;

/**
 * Created by Administrator on 2016/1/12.
 */
public class BaseActivity extends AppCompatActivity implements Container {

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
    public FrameLayout getContentView() {
        return mContentParent;
    }

    @Override
    public ViewExpansionDelegate getViewExpansionDelegate() {
        if (mViewExpansionDelegate == null) {
            mViewExpansionDelegate = ViewExpansionDelegateProvider.DEFAULT.createViewExpansionDelegate(this);
        }
        return mViewExpansionDelegate;
    }


}
