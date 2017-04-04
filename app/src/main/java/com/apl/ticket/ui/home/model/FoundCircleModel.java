package com.apl.ticket.ui.home.model;

import com.apl.ticket.api.Api;
import com.apl.ticket.api.ApiService;
import com.apl.ticket.been.FoundCircleBoardBeen;
import com.apl.ticket.constants.HttpConstans;
import com.apl.ticket.ui.home.contract.FoundCircleContract;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vittaw.mvplibrary.event.AndroidIOToMain;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class FoundCircleModel implements FoundCircleContract.Model{
    @Override
    public Observable<FoundCircleBoardBeen> getFoundCircleBoardBeen() {
        Gson gson = new GsonBuilder()
                .setLenient()//使解析更宽容
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))//在这里传进去了
                .baseUrl(HttpConstans.FOUND_CIRCLE)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        return apiService.getFoundCircleBoardBeen().compose(new AndroidIOToMain.IOToMainTransformer<FoundCircleBoardBeen>());
    }
}
