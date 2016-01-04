package com.zsx.rxjavatest.ui.activity;

import com.zsx.rxjavatest.ui.mvp.MvpActivity;

import java.util.List;

/**
 * MainActivity接口
 */
public interface IMainActivity extends MvpActivity {

    void showRecyclerView(List<String> data);

}
