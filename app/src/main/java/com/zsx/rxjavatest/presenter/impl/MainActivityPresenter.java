package com.zsx.rxjavatest.presenter.impl;

import android.os.Handler;

import com.google.gson.Gson;
import com.zsx.rxjavatest.data.DataManager;
import com.zsx.rxjavatest.data.api.ApiService;
import com.zsx.rxjavatest.data.api.StringConverterFactory;
import com.zsx.rxjavatest.presenter.BasePresenter;
import com.zsx.rxjavatest.ui.activity.AbMainActivity;

import java.util.List;
import java.util.Map;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivityPresenter extends BasePresenter<AbMainActivity> {

    public void loadData() {
        DataManager.getTestData().subscribe(new Subscriber<List<String>>() {
            @Override
            public void onCompleted() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getMvpView().dismissLoadingDialog();
                    }
                }, 2000);
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

    public void login() {
        getMvpView().showLoadingDialog();
        ApiService apiService = ApiService.Creator.newApiService();
        apiService.postData("username", "password")
                .subscribeOn(Schedulers.io())
//                .observeOn()
                .subscribe(new Subscriber<Map>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Map map) {
                        System.out.println(map.toString());
                    }
                });
    }


    public void loadString() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.173.235.82:30453/dataplatform/")
                .addConverterFactory(new StringConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getObservableData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Map>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Map map) {
                        System.out.println(map.size());
                    }
                });
    }


}
