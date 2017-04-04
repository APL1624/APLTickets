package com.apl.ticket.ui.home.contract;

import com.apl.ticket.been.mine.MineInfoBean;
import com.vittaw.mvplibrary.base.BaseModel;
import com.vittaw.mvplibrary.base.BasePresenter;
import com.vittaw.mvplibrary.base.BaseView;

import java.util.List;

import rx.Observable;


/**
 * Created by Administrator on 2017/3/29.
 */

public interface MineContract {
    interface Model extends BaseModel{
        Observable<MineInfoBean> getMineInfoBean();
    }
    //定义presenter
    abstract class Presenter extends BasePresenter<Model,View>{

        public abstract void getMineInfoBean();

    }
    //定义view
    interface View extends BaseView {
        public abstract void setMineInfoBean(List<MineInfoBean> mMineData);
    }



}
