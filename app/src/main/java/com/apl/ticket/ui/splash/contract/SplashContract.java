package com.apl.ticket.ui.splash.contract;

import com.apl.ticket.been.SplashBeen;
import com.vittaw.mvplibrary.base.BaseModel;
import com.vittaw.mvplibrary.base.BasePresenter;
import com.vittaw.mvplibrary.base.BaseView;

import java.util.Map;

import rx.Observable;

/**
 * 规划Splash的MVP 流程 : 因为splash要网络请求
 */

public interface SplashContract {

    //定义Presenter
    abstract class Presenter extends BasePresenter<Model,View>{
        //TODO 暂时没有传递参数,可根据接口url添加参数
       public abstract void getSplashBeen(Map<String ,String > map);
    }


    //定义Model
    interface Model extends BaseModel{
        Observable<SplashBeen> getSplashBeen(Map<String ,String > map);
    }


    //定义View
    interface View extends BaseView{

        void returnSplashBeen(SplashBeen splashBeen);

    }

}
