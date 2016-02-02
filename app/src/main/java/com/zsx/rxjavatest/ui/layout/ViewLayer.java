package com.zsx.rxjavatest.ui.layout;

import android.support.annotation.NonNull;
import android.widget.FrameLayout;

import com.zsx.rxjavatest.ui.expansion.ViewExpansionDelegate;

/**
 * Activity,Fragment 接口 (容器)
 */
public interface ViewLayer {

    @NonNull
    FrameLayout getContentView();

    /**
     * 获取页面扩展类
     *
     * @return
     */
    ViewExpansionDelegate getViewExpansionDelegate();


    boolean isFinishing();

}
