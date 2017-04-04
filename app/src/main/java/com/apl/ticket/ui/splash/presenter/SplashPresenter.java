package com.apl.ticket.ui.splash.presenter;

import com.apl.ticket.been.SplashBeen;
import com.apl.ticket.ui.splash.contract.SplashContract;

import java.util.Map;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/24 0024.
 */

public class SplashPresenter extends SplashContract.Presenter{

    @Override
    public void getSplashBeen(Map<String ,String > map) {
        mModel.getSplashBeen(map).subscribe(new Subscriber<SplashBeen>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                //将网络请求失败的结果抛回view
                mView.onError(e.getCause().getMessage() + "网络请求失败!");
            }

            @Override
            public void onNext(SplashBeen splashBeen) {
                //将网络请求成功的结果抛回view
                mView.returnSplashBeen(splashBeen);
            }
        });
    }

}
