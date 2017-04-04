package com.apl.ticket.ui.home.model;

import com.apl.ticket.api.Api;
import com.apl.ticket.been.CinemaClassifyBeen;
import com.apl.ticket.been.cinema.CinemaListBeen;
import com.apl.ticket.ui.home.contract.CinemaContract;
import com.vittaw.mvplibrary.event.AndroidIOToMain;

import java.util.Map;

import rx.Observable;

public class CinemaModel implements CinemaContract.Model {

    @Override
    public Observable<CinemaListBeen> getCinemaListBeen(Map<String, String> map) {
        return Api.getApiService().getCinemaListBeen(map).compose(new AndroidIOToMain.IOToMainTransformer<CinemaListBeen>());
    }

    @Override
    public Observable<CinemaClassifyBeen> getCinemaClassifyBeen(Map<String, String> map) {
        return Api.getApiService().getCinemaClassifyBeen(map).compose(new AndroidIOToMain.IOToMainTransformer<CinemaClassifyBeen>());
    }
}
