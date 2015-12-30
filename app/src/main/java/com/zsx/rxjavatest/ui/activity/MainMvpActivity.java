package com.zsx.rxjavatest.ui.activity;

import com.zsx.rxjavatest.ui.base.ActivityMvpView;

import java.util.List;

/**
 * Created by Administrator on 2015/12/28.
 */
public interface MainMvpActivity extends ActivityMvpView {

    void showRecyclerView(List<String> data);

}
