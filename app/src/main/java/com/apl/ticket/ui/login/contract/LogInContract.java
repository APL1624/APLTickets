package com.apl.ticket.ui.login.contract;

import com.vittaw.mvplibrary.base.BaseModel;
import com.vittaw.mvplibrary.base.BasePresenter;
import com.vittaw.mvplibrary.base.BaseView;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface LogInContract {
    //定义presenter
    abstract class Presenter extends BasePresenter<Model,View> {

    }
    //定义model
    interface Model extends BaseModel {

    }

    //定义view
    interface View extends BaseView {

    }
}
