package com.apl.ticket.ui.home.model;

import com.apl.ticket.api.Api;
import com.apl.ticket.been.FoundReadBeen;
import com.apl.ticket.been.FoundReadTabBeen;
import com.apl.ticket.ui.home.contract.FoundReadContract;
import com.vittaw.mvplibrary.event.AndroidIOToMain;

import java.util.Map;

import rx.Observable;

/**
 * Created by Administrator on 2017/4/3 0003.
 */

public class FoundReadModel implements FoundReadContract.Model {

    @Override
    public Observable<FoundReadTabBeen> getFoundReadTabBeen() {
        return Api.getApiService().getFoundReadTabBeen().compose(new AndroidIOToMain.IOToMainTransformer<FoundReadTabBeen>());
    }
}
