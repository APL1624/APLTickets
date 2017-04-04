package com.apl.ticket.ui.home.contract;

import com.apl.ticket.been.HomePageBeen;
import com.vittaw.mvplibrary.base.BaseModel;
import com.vittaw.mvplibrary.base.BasePresenter;
import com.vittaw.mvplibrary.base.BaseView;

import rx.Observable;



public interface MovieHotContract {

    //定义presenter
    abstract class Presenter extends BasePresenter<Model,View>{
        //此方法public abstract不可少
       public abstract void getHomePageBeen(String type,String city);

    }


    //定义model
    interface Model extends BaseModel{

        Observable<HomePageBeen> getHomePageBeen(String type,String city);

    }

    //定义view
    interface View extends BaseView{

        void returnHomePageBeen(HomePageBeen homePageBeen);

    }

}
