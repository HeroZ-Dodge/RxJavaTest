package com.zsx.rxjavatest.presenter;

import com.zsx.rxjavatest.ui.layout.ViewLayer;

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
public interface Presenter<V extends ViewLayer> {

    void attachView(V container); // 绑定

    void detachView(); // 解除绑定
}
