package com.apl.ticket.ui.home.presenter;

import com.apl.ticket.been.FoundCircleBoardBeen;
import com.apl.ticket.ui.home.contract.FoundCircleContract;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class FoundCirclePresenter extends FoundCircleContract.Presenter {
    @Override
    public void getFoundCircleBoardBeen() {
        mModel.getFoundCircleBoardBeen().subscribe(new Subscriber<FoundCircleBoardBeen>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onError("网络出错了!");
            }

            @Override
            public void onNext(FoundCircleBoardBeen foundCircleBoardBeen) {
                mView.returnFoundCircleBoardBeen(foundCircleBoardBeen);
            }
        });
    }
}
