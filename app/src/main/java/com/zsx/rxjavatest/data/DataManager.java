package com.zsx.rxjavatest.data;

import android.content.Context;

import com.zsx.rxjavatest.data.api.ApiService;
import com.zsx.rxjavatest.data.local.PreferencesHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import rx.Observable;

/**
 * Created by Administrator on 2015/12/29.
 */
public class DataManager {

    private static DataManager mInstance;
    private PreferencesHelper mPreferencesHelper;
    private ApiService mApiService;



    private DataManager(Context context) {
        mPreferencesHelper = PreferencesHelper.getInstance(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.173.235.82:30453/dataplatform/")
                .build();
        mApiService = retrofit.create(ApiService.class);
    }

    public static DataManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DataManager.class) {
                mInstance = new DataManager(context);
            }
        }
        return mInstance;
    }

    public PreferencesHelper getPreferencesHelper(Context context) {
        return getInstance(context).mPreferencesHelper;
    }

    public static String getString (Context context, String key) {
        return PreferencesHelper.getString(context, key);
    }



    /* 获取测试数据 */
    public static Observable<List<String>> getTestData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add("index = " + i);
        }
        return Observable.just(data);
    }




}
