package com.apl.ticket.ui.foundcircledetail.presenter;

import com.apl.ticket.been.FoundCircleDetailPostBeen;
import com.apl.ticket.ui.foundcircledetail.contract.CircleDetailContract;

import rx.Subscriber;


public class CircleDetailPresenter extends CircleDetailContract.Presenter {
    @Override
    public void getCircleDetailBeen(String sort, String boardId,String maxId) {
        mView.onStartLoad();
        mModel.getCircleDetailBeen(sort,boardId,maxId).subscribe(new Subscriber<FoundCircleDetailPostBeen>() {
            @Override
            public void onCompleted() {
                mView.onStopLoad();
            }

            @Override
            public void onError(Throwable e) {
                mView.onError("网络出错了!");
            }

            @Override
            public void onNext(FoundCircleDetailPostBeen circleDetailPostBeen) {
                mView.returnCircleDetailBeen(circleDetailPostBeen);
            }
        });

    }
}
