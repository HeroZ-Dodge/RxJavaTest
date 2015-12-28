package com.zsx.rxjavatest.presenter.main;

import com.zsx.rxjavatest.presenter.base.BasePresenter;
import com.zsx.rxjavatest.ui.base.MainMvpActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2015/12/28.
 */
public class MainPresenter extends BasePresenter<MainMvpActivity> {

    public void loadData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add("index = " + i);
        }
        Observable<List<String>> observable = Observable.just(data);
        observable.subscribe(new Subscriber<List<String>>() {

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<String> data) {
                getMvpView().showRecyclerView(data);
            }

        });
    }


}
