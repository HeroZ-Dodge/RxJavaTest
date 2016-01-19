package com.zsx.rxjavatest.ui.layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.zsx.rxjavatest.ui.expansion.ViewExpansionDelegate;

/**
 * Created by Administrator on 2016/1/13.
 */
public class BaseFragment extends Fragment implements Container {


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
        return null;
    }

    @Override
    public ViewExpansionDelegate getViewExpansionDelegate() {
        return null;
    }
}
