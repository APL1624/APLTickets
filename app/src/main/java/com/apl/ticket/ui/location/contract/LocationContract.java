package com.apl.ticket.ui.location.contract;

import com.apl.ticket.been.location.LocationCityBean;
import com.vittaw.mvplibrary.base.BaseModel;
import com.vittaw.mvplibrary.base.BasePresenter;
import com.vittaw.mvplibrary.base.BaseView;

import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 *fqc,cry,cry.
 */

public interface LocationContract {
    //定义Presenter
    abstract class Presenter extends BasePresenter<Model,View> {
        public abstract void handleModelToView();


    }
    //定义Model
    interface Model extends BaseModel {
        public abstract Observable<String> getCityList(String key);
    }


    //定义View
    interface View extends BaseView {
        public abstract void showLocationData(List<LocationCityBean> cityInfo);
        public abstract String setModelKey();


    }
}
