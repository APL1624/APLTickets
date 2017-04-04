package com.apl.ticket.api;

import android.util.Log;

import com.apl.ticket.constants.HttpConstans;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * fqc,cry,cry
 */

public class LocationApi {

    private static final String TAG = LocationApi.class.getSimpleName();
    private static ApiService apiService;

    public static ApiService getApiService(){
        if (apiService == null){
            initApiService();
        }
        return apiService;
    }

    private static void initApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(HttpConstans.HOST_LOCATION)
                .build();
        apiService = retrofit.create(ApiService.class);
    }

}
