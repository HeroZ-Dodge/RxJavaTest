package com.zsx.rxjavatest.ui.layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.zsx.rxjavatest.ui.expansion.ViewExpansionDelegate;
import com.zsx.rxjavatest.ui.expansion.ViewExpansionDelegateProvider;

/**
 * Fragment 抽象类型
 */
public class BaseFragment extends Fragment implements Container {

    private FrameLayout mContentParent;
    private ViewExpansionDelegate mViewExpansionDelegate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
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
