package com.zsx.rxjavatest.ui.activity.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.zsx.rxjavatest.presenter.impl.GlidePresenter;
import com.zsx.rxjavatest.ui.activity.IGlideActivity;
import com.zsx.rxjavatest.ui.layout.BaseActivity;

import java.util.concurrent.TimeUnit;

import rx.Scheduler;

/**
 * Glide test activity
 */
public class GlideActivity extends BaseActivity<GlidePresenter> implements IGlideActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    protected GlidePresenter createPresenter() {
        GlidePresenter presenter = new GlidePresenter();
        presenter.attachView(this);
        return presenter;
    }

    @Override
    public void showImagies() {

    }
}
