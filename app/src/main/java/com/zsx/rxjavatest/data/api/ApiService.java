package com.zsx.rxjavatest.data.api;

import java.util.Map;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2015/12/30.
 */
public interface ApiService {

    String BASE_URL = "http://10.173.235.82:30453/dataplatform/";

    @GET("car/queryCarType")
    Call<Map> getTestData();

    @GET("car/queryCarType")
    Observable<Map> getObservableData();

    @FormUrlEncoded
    @POST("appuser/login")
    Observable<Map> postData(@Field(value = "phone_number") String userName,
                             @Field(value = "password") String pw);

    class Creator {

        public static ApiService newApiService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(ApiService.class);
        }
    }


}
