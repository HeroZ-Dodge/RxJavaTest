package com.zsx.rxjavatest.presenter.impl;

import android.os.Handler;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;
import com.zsx.rxjavatest.data.DataManager;
import com.zsx.rxjavatest.data.api.ApiService;
import com.zsx.rxjavatest.presenter.BasePresenter;
import com.zsx.rxjavatest.ui.activity.MainMvpActivity;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import retrofit.Converter;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2015/12/28.
 */
public class MainPresenter extends BasePresenter<MainMvpActivity> {

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
                .addConverterFactory(new ToStringConverter())
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


    static class ToStringConverter extends Converter.Factory {

        @Override
        public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
            //noinspection EqualsBetweenInconvertibleTypes
            if (String.class.equals(type)) {
                return new Converter<ResponseBody, Object>() {

                    @Override
                    public Object convert(ResponseBody responseBody) throws IOException {
                        return responseBody.string();
                    }
                };
            }

            return null;
        }

        @Override
        public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
            //noinspection EqualsBetweenInconvertibleTypes
            if (String.class.equals(type)) {
                return new Converter<String, RequestBody>() {

                    @Override
                    public RequestBody convert(String value) throws IOException {
                        return RequestBody.create(MediaType.parse("text/plain"), value);
                    }
                };
            }

            return null;
        }

    }

}
