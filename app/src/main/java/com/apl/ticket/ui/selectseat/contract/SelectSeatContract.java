package com.apl.ticket.ui.selectseat.contract;

import com.vittaw.mvplibrary.base.BaseModel;
import com.vittaw.mvplibrary.base.BasePresenter;
import com.vittaw.mvplibrary.base.BaseView;

/**
 * Created by Administrator on 2017/3/29.
 */

public interface SelectSeatContract {
    //定义Presenter
    abstract class Presenter extends BasePresenter<Model,View> {
    }
    //定义Model
    interface Model extends BaseModel {
    }
    //定义View
    interface View extends BaseView {
    }

}
