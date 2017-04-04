package com.apl.ticket.ui.home.model;

import com.apl.ticket.api.Api;
import com.apl.ticket.been.FoundImageBeen;
import com.apl.ticket.been.HomePageBeen;
import com.apl.ticket.ui.home.contract.FoundContract;
import com.apl.ticket.ui.home.contract.MovieHotContract;
import com.vittaw.mvplibrary.base.BaseModel;
import com.vittaw.mvplibrary.event.AndroidIOToMain;

import java.util.Map;

import rx.Observable;

/**
 * Created by Administrator on 2017/3/28.
 */

public class FoundModel implements FoundContract.Model {


    @Override
    public Observable<FoundImageBeen> getFoundImageBeen(Map<String, String> map) {
        return Api.getApiService().getFoundImageBeen(map).compose(new AndroidIOToMain.IOToMainTransformer<FoundImageBeen>());
    }
}
