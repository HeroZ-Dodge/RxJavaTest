package com.zsx.rxjavatest.presenter.impl;

import com.zsx.rxjavatest.presenter.BasePresenter;
import com.zsx.rxjavatest.ui.activity.IGlideActivity;

/**
 * Created by Administrator on 2016/3/3.
 */
public class GlidePresenter extends BasePresenter<IGlideActivity> {

    public void loadImage() {
        getViewLayer().showImagies();
    }

}
