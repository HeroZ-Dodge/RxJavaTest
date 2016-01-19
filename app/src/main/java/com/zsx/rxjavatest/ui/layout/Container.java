package com.zsx.rxjavatest.ui.layout;

import android.widget.FrameLayout;

import com.zsx.rxjavatest.ui.expansion.ViewExpansionDelegate;

/**
 * Activity,Fragment 接口
 */
public interface Container {

    FrameLayout getContentView();

    ViewExpansionDelegate getViewExpansionDelegate();

}
