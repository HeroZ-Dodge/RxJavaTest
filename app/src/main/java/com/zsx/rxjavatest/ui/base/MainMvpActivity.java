package com.zsx.rxjavatest.ui.base;

import java.util.List;

/**
 * Created by Administrator on 2015/12/28.
 */
public interface MainMvpActivity extends BaseActivityView {


    void showRecyclerView(List<String> data);

    void showErrorView();



}
