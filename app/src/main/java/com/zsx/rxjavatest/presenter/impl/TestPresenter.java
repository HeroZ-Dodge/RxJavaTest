package com.zsx.rxjavatest.presenter.impl;

import android.os.Handler;

import com.zsx.rxjavatest.presenter.BasePresenter;
import com.zsx.rxjavatest.ui.activity.ITestActivity;
import com.zsx.rxjavatest.ui.activity.impl.TestActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/20.
 */
public class TestPresenter extends BasePresenter<ITestActivity> {

    public List<String> getList() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            stringList.add("index  = " + i);
        }
        return stringList;
    }


    public void loadList() {
        getViewLayer().getViewExpansionDelegate().showProgressPage();
        getViewLayer().showList(getList());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getViewLayer().getViewExpansionDelegate().dismissProgressPage();
            }
        }, 2000);
    }

}
