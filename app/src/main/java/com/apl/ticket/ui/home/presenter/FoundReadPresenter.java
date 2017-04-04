package com.apl.ticket.ui.home.presenter;

import com.apl.ticket.been.FoundReadBeen;
import com.apl.ticket.been.FoundReadTabBeen;
import com.apl.ticket.ui.home.contract.FoundReadContract;

import java.util.Map;

import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/4/3 0003.
 */

public class FoundReadPresenter extends FoundReadContract.Presenter {

    @Override
    public void getFoundReadTabBeen() {
        mModel.getFoundReadTabBeen().subscribe(new Action1<FoundReadTabBeen>() {
            @Override
            public void call(FoundReadTabBeen foundReadTabBeen) {
                mView.returnFoundTabReadBeen(foundReadTabBeen);
            }
        });
    }


}
