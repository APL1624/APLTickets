package com.apl.ticket.ui.threatredetail.contract;

import com.apl.ticket.been.theatre.ThreatreBean;
import com.apl.ticket.ui.login.contract.LogInContract;
import com.vittaw.mvplibrary.base.BaseModel;
import com.vittaw.mvplibrary.base.BasePresenter;
import com.vittaw.mvplibrary.base.BaseView;

import java.util.Map;

import rx.Observable;


/**
 * Created by Administrator on 2017/3/31.
 */

public interface TheatreDetailContract {
    interface Model extends BaseModel{
       Observable<ThreatreBean> getThreatreBean(Map<String,String> map);
    }
    interface View extends BaseView {
        abstract void showViewpager(ThreatreBean threatreBean);

    }
    abstract class Presenter extends BasePresenter<Model,View>{
        protected abstract void getThreatreBean(Map<String, String> map);
    }
}
