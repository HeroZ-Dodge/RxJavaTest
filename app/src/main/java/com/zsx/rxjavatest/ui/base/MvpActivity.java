package com.zsx.rxjavatest.ui.base;

/**
 * MVP activity 基础接口
 */
public interface MvpActivity extends MvpView {

    void showErrorView();

    void showLoadingDialog();

    void dismissLoadingDialog();

}
