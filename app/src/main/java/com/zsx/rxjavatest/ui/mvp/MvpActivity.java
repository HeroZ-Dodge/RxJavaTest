package com.zsx.rxjavatest.ui.mvp;

/**
 * MVP activity 基础接口
 */
public interface MvpActivity extends MvpView {

    void showErrorView();

    void showEmptyView();

    void showContentView();

    void showLoadingDialog();

    void dismissLoadingDialog();

}
