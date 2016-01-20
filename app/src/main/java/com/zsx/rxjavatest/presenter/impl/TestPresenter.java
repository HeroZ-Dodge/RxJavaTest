package com.zsx.rxjavatest.presenter.impl;

import android.os.Handler;

import com.zsx.rxjavatest.presenter.BasePresenter;
import com.zsx.rxjavatest.ui.activity.impl.TestActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/20.
 */
public class TestPresenter extends BasePresenter<TestActivity> {

    public List<String> getList() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            stringList.add("intdex  = " + i);
        }
        return stringList;
    }


    public void loadList() {
        getContainer().getViewExpansionDelegate().showProgressPage();
        getContainer().showList(getList());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getContainer().getViewExpansionDelegate().dismissProgressPage();
            }
        }, 2000);
    }

}
