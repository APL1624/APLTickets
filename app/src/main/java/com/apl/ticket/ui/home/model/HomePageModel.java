package com.apl.ticket.ui.home.model;

import com.apl.ticket.api.Api;
import com.apl.ticket.been.HomePageBeen;
import com.apl.ticket.ui.home.contract.MovieHotContract;
import com.vittaw.mvplibrary.event.AndroidIOToMain;

import rx.Observable;

/**
 * Created by Administrator on 2017/3/26 0026.
 */

public class HomePageModel implements MovieHotContract.Model {

    @Override
    public Observable<HomePageBeen> getHomePageBeen(String type, String city) {
        //就这样完成了一个网络请求
        return Api.getApiService().getHomePageBeen(type,city).compose(new AndroidIOToMain.IOToMainTransformer<HomePageBeen>());
    }
}
