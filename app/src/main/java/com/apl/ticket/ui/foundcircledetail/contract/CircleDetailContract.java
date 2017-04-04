package com.apl.ticket.ui.foundcircledetail.contract;

import com.apl.ticket.been.FoundCircleDetailPostBeen;
import com.vittaw.mvplibrary.base.BaseModel;
import com.vittaw.mvplibrary.base.BasePresenter;
import com.vittaw.mvplibrary.base.BaseView;

import rx.Observable;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public interface CircleDetailContract {


    abstract class Presenter extends BasePresenter<Model,View>{

        public abstract void getCircleDetailBeen(String sort, String boardId,String maxId);

    }

    interface Model extends BaseModel {

        Observable<FoundCircleDetailPostBeen> getCircleDetailBeen(String sort,String boardId,String maxId);

    }

    interface View extends BaseView{

        void returnCircleDetailBeen(FoundCircleDetailPostBeen circleDetailPostBeen);

    }

}
