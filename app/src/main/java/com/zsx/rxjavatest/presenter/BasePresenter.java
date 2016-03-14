package com.zsx.rxjavatest.presenter;

import android.content.Context;

import com.zsx.rxjavatest.ui.layout.ViewLayer;
import com.zsx.rxjavatest.ui.layout.ViewLayerProxy;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public abstract class BasePresenter<V extends ViewLayer> implements Presenter<V> {

    private Context mContext;
    private V mViewLayer; // 视图层

    @Override
    public void attachView(V viewLayer) {
        mViewLayer = (V) ViewLayerProxy.createProxy(viewLayer);
        mContext = viewLayer.getContentView().getContext();
//        mViewLayer = viewLayer;
    }

    @Override
    public void detachView() {
        mViewLayer = null;
    }

    public boolean isViewAttached() {
        return mViewLayer != null;
    }

    public V getViewLayer() {
        return mViewLayer;
    }

    public Context getContext() {
        return mContext;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(ViewLayer) before" +
                    " requesting data to the Presenter");
        }
    }
}