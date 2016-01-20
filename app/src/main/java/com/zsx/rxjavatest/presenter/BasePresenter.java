package com.zsx.rxjavatest.presenter;

import com.zsx.rxjavatest.ui.layout.Container;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public abstract class BasePresenter<T extends Container> implements Presenter<T> {

    private T mContainer;

    @Override
    public void attachView(T container) {
        mContainer = container;
    }

    @Override
    public void detachView() {
        mContainer = null;
    }

    public boolean isViewAttached() {
        return mContainer != null;
    }

    public T getContainer() {
        return mContainer;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}