package com.apl.ticket.ui.home.presenter;

import com.apl.ticket.been.FoundReadBeen;
import com.apl.ticket.ui.home.contract.FoundReadContentContract;

import java.util.Map;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/4/3 0003.
 */

public class FoundReadContentPresenter extends FoundReadContentContract.Presenter {
    @Override
    public void getFoundReadBeen(Map<String, String> map) {
        mView.onStartLoad();
        mModel.getFoundReadBeen(map).subscribe(new Subscriber<FoundReadBeen>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                mView.onError("网络出错了!");
            }

            @Override
            public void onNext(FoundReadBeen foundReadBeen) {
                mView.returnFoundReadBeen(foundReadBeen);
            }
        });
        mView.onStopLoad();
    }
}
