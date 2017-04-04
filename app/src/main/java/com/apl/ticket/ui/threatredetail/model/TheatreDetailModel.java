package com.apl.ticket.ui.threatredetail.model;

import com.apl.ticket.api.Api;
import com.apl.ticket.been.theatre.ThreatreBean;
import com.apl.ticket.ui.threatredetail.contract.TheatreDetailContract;
import com.vittaw.mvplibrary.event.AndroidIOToMain;

import java.util.Map;

import rx.Observable;


/**
 * Created by Administrator on 2017/3/31.
 */

public class TheatreDetailModel implements TheatreDetailContract.Model {

    @Override
    public Observable<ThreatreBean> getThreatreBean(Map<String, String> map) {
        return Api.getApiService().getThreatreBeen(map).compose(new AndroidIOToMain.IOToMainTransformer<ThreatreBean>());

    }
}
