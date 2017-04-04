package com.apl.ticket.ui.moviedetail.contract;

import com.apl.ticket.been.HomeDetailBeen;
import com.apl.ticket.been.detailcomment.HomeDetailCommentBeen;
import com.vittaw.mvplibrary.base.BaseModel;
import com.vittaw.mvplibrary.base.BasePresenter;
import com.vittaw.mvplibrary.base.BaseView;

import java.util.Map;

import rx.Observable;


public interface MovieDetailContract {

    //定义presenter
    abstract class Presenter extends BasePresenter<Model,View>{

        public abstract void getHomeDetail(String account_id,String movie_id,String city);

        public abstract void getHomeDetailComment(Map<String,String> map);

    }


    //定义model
    interface Model extends BaseModel{

        Observable<HomeDetailBeen> getHomeDetail(String account_id,String movie_id,String city);

        Observable<HomeDetailCommentBeen> getHomeDetailComment(Map<String,String> map);

    }


    //定义view
    interface View extends BaseView{

        void returnHomeDetailBeen(HomeDetailBeen homeDetailBeen);

        void returnHomeDetailComment(HomeDetailCommentBeen homeDetailCommentBeen);

    }


}
