package com.apl.ticket.ui.home.contract;


import com.apl.ticket.been.CinemaClassifyBeen;
import com.apl.ticket.been.cinema.CinemaListBeen;
import com.vittaw.mvplibrary.base.BaseModel;
import com.vittaw.mvplibrary.base.BasePresenter;
import com.vittaw.mvplibrary.base.BaseView;

import java.util.Map;

import rx.Observable;

public interface CinemaContract {

    //定义presenter
    abstract class Presenter extends BasePresenter<Model,View>{

        public abstract void getCinemaListBeen(Map<String,String> map);

        public abstract void getCinemaClassifyBeen(Map<String,String> map);

    }


    //定义model
    interface Model extends BaseModel{

        //movie_id=48143&city=110000&apiVer=21&apilevel=17
        Observable<CinemaListBeen> getCinemaListBeen(Map<String,String> map);

        Observable<CinemaClassifyBeen> getCinemaClassifyBeen(Map<String,String> map);

    }

    //定义view
    interface View extends BaseView{

        void returnCinemaListBeen(CinemaListBeen cinemaListBeen);

        void returnCinemaClassifyBeen(CinemaClassifyBeen cinemaClassifyBeen);

    }

}
