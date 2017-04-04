package com.apl.ticket.ui.home.contract;

import com.apl.ticket.been.FoundCircleBoardBeen;
import com.vittaw.mvplibrary.base.BaseModel;
import com.vittaw.mvplibrary.base.BasePresenter;
import com.vittaw.mvplibrary.base.BaseView;

import rx.Observable;


public interface FoundCircleContract {

    abstract class Presenter extends BasePresenter<Model,View>{

        public abstract void getFoundCircleBoardBeen();

    }


    interface Model extends BaseModel{

        Observable<FoundCircleBoardBeen> getFoundCircleBoardBeen();//没有请求参数

    }


    interface View extends BaseView{
        void returnFoundCircleBoardBeen(FoundCircleBoardBeen foundCircleBoardBeen);
    }

}
