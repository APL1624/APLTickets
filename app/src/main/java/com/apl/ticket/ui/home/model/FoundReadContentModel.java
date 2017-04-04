package com.apl.ticket.ui.home.model;

import com.apl.ticket.api.Api;
import com.apl.ticket.been.FoundReadBeen;
import com.apl.ticket.ui.home.contract.FoundReadContentContract;
import com.vittaw.mvplibrary.event.AndroidIOToMain;

import java.util.Map;

import rx.Observable;

/**
 * Created by Administrator on 2017/4/3 0003.
 */

public class FoundReadContentModel implements FoundReadContentContract.Model {
    @Override
    public Observable<FoundReadBeen> getFoundReadBeen(Map<String, String> map) {
        return Api.getApiService().getFoundReadBeen(map).compose(new AndroidIOToMain.IOToMainTransformer<FoundReadBeen>());
    }
}
