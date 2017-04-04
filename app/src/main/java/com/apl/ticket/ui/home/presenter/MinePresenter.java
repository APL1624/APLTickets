package com.apl.ticket.ui.home.presenter;

import android.util.Log;

import com.apl.ticket.been.mine.MineInfoBean;
import com.apl.ticket.ui.home.contract.MineContract;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/29.
 */

public class MinePresenter extends MineContract.Presenter {
    private static final String TAG =MinePresenter.class.getSimpleName() ;
    private List<MineInfoBean> mMineData=new ArrayList<>();
    @Override
    public void getMineInfoBean() {
        mModel.getMineInfoBean().subscribe(new Subscriber<MineInfoBean>() {
            @Override
            public void onCompleted() {
                mView.setMineInfoBean(mMineData);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MineInfoBean mineInfoBeen) {
                mMineData.add(mineInfoBeen);

            }
        });
    }
}
