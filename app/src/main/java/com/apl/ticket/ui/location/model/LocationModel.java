package com.apl.ticket.ui.location.model;




import android.util.Log;

import com.apl.ticket.api.LocationApi;
import com.apl.ticket.ui.location.contract.LocationContract;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * fqc,cry,cry
 */

public class LocationModel implements LocationContract.Model {


    private static final String TAG =LocationModel.class.getSimpleName() ;

    @Override
    public Observable<String> getCityList(String key) {

        Observable<String> locationCityList = LocationApi.getApiService().getLocationCityList(key);
        Log.e(TAG,locationCityList.toString());
        return locationCityList.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
