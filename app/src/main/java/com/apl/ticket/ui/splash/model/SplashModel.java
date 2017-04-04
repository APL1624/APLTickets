package com.apl.ticket.ui.splash.model;

import com.apl.ticket.api.Api;
import com.apl.ticket.been.SplashBeen;
import com.apl.ticket.ui.splash.contract.SplashContract;
import com.vittaw.mvplibrary.event.AndroidIOToMain;

import java.util.Map;

import rx.Observable;


public class SplashModel implements SplashContract.Model {

    @Override
    public Observable<SplashBeen> getSplashBeen(Map<String ,String> map) {
        return Api.getApiService().getSplashBeen(map).compose(new AndroidIOToMain.IOToMainTransformer<SplashBeen>());
    }
}
