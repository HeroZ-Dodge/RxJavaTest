package com.zsx.rxjavatest.ui.base;

/**
 * Created by Administrator on 2015/12/28.
 */
public interface ActivityMvpView extends MvpView {

    void showErrorView();

    void showLoadingDialog();

    void dismissLoadingDialog();

}
