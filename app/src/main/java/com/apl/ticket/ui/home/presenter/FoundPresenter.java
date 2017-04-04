package com.apl.ticket.ui.home.presenter;

import com.apl.ticket.been.FoundImageBeen;
import com.apl.ticket.ui.home.contract.FoundContract;

import java.util.Map;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/29.
 */

public class FoundPresenter extends FoundContract.Presenter {

    @Override
    public void getFoundImageBeen(Map<String, String> map) {
        mModel.getFoundImageBeen(map).subscribe(new Subscriber<FoundImageBeen>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onError(e.getCause()+"网络出错啦!");
            }

            @Override
            public void onNext(FoundImageBeen foundImageBeen) {
                mView.returnFoundImageBeen(foundImageBeen);
            }
        });
    }
}
