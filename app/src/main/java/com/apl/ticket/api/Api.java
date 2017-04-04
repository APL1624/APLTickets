package com.apl.ticket.api;

import com.apl.ticket.constants.HttpConstans;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 又封装一个Api工具类,使整个程序的ApiService只有一份
 */

public class Api {

    private static ApiService apiService;

    public static ApiService getApiService(){
        if (apiService == null){
            initApiService(HttpConstans.HOST_NAME);
        }
        return apiService;
    }

    private static void initApiService(String host) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(host)
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static ApiService getMovieDetailApiService(){
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(HttpConstans.MOVIE_DETAIL_COMMENT_HOST)
                .build();
        return retrofit.create(ApiService.class);
    }

}
