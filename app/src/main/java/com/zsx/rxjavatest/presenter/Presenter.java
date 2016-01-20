package com.zsx.rxjavatest.presenter;

import com.zsx.rxjavatest.ui.layout.Container;

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
public interface Presenter<V extends Container> {

    void attachView(V container);

    void detachView();
}
