package com.apl.ticket.ui.home.presenter;

import android.util.Log;

import com.apl.ticket.been.CinemaClassifyBeen;
import com.apl.ticket.been.cinema.CinemaListBeen;
import com.apl.ticket.ui.home.contract.CinemaContract;
import com.orhanobut.logger.Logger;

import java.util.Map;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/28 0028.
 */

public class CinemaPresenter extends CinemaContract.Presenter {

    @Override
    public void getCinemaListBeen(Map<String, String> map) {
        mView.onStartLoad();
        mModel.getCinemaListBeen(map).subscribe(new Subscriber<CinemaListBeen>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onError(e.getCause().getMessage()+"网络出错了");
                Logger.e(e.getCause().getMessage());
            }

            @Override
            public void onNext(CinemaListBeen cinemaListBeen) {
                mView.returnCinemaListBeen(cinemaListBeen);
                mView.onStopLoad();
            }
        });
    }

    @Override
    public void getCinemaClassifyBeen(Map<String, String> map) {
        mModel.getCinemaClassifyBeen(map).subscribe(new Subscriber<CinemaClassifyBeen>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onError(e.getCause().getMessage() + "网络出错了");
            }

            @Override
            public void onNext(CinemaClassifyBeen cinemaClassifyBeen) {
                mView.returnCinemaClassifyBeen(cinemaClassifyBeen);
            }
        });
    }
}
