package com.zsx.rxjavatest.ui.activity;

import com.zsx.rxjavatest.ui.base.BaseActivity;

import java.util.List;

/**
 * MainActivity接口
 */
public abstract class AbMainActivity extends BaseActivity {

    public abstract void showRecyclerView(List<String> data);

}
