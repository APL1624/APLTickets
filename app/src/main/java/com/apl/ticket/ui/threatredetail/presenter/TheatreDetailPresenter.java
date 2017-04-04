package com.apl.ticket.ui.threatredetail.presenter;

import android.util.Log;

import com.apl.ticket.been.theatre.ThreatreBean;
import com.apl.ticket.ui.threatredetail.contract.TheatreDetailContract;

import java.util.Map;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/4/1.
 */

public class TheatreDetailPresenter extends TheatreDetailContract.Presenter {
    private static final String TAG =TheatreDetailPresenter.class.getSimpleName() ;

    @Override
    public void getThreatreBean(Map<String, String> map) {
        mModel.getThreatreBean(map).subscribe(new Subscriber<ThreatreBean>() {
            @Override
            public void onCompleted() {
                Log.e(TAG,"succeed");

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,e.getMessage()+"error");
            }

            @Override
            public void onNext(ThreatreBean threatreBean) {


                mView.showViewpager(threatreBean);
            }
        });
    }
}
