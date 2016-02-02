package com.zsx.rxjavatest.presenter.impl;

import android.os.Handler;

import com.zsx.rxjavatest.presenter.BasePresenter;
import com.zsx.rxjavatest.ui.activity.ITestTwoActivity;

/**
 * Created by Administrator on 2016/1/29.
 */
public class TestTwoPresenter extends BasePresenter<ITestTwoActivity> {

    public void getList() {
        getViewLayer().getViewExpansionDelegate().showProgressDialog("one test");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getViewLayer().onTest();
                getViewLayer().getViewExpansionDelegate().dismissProgressDialog();
            }
        }, 3000);
    }



}
