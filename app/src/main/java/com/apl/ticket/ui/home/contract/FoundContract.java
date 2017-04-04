package com.apl.ticket.ui.home.contract;

import com.apl.ticket.been.FoundImageBeen;
import com.vittaw.mvplibrary.base.BaseModel;
import com.vittaw.mvplibrary.base.BasePresenter;
import com.vittaw.mvplibrary.base.BaseView;

import java.util.Map;

import rx.Observable;

/**
 * Created by Administrator on 2017/3/29.
 */

public interface FoundContract {

    //定义presenter
    abstract class Presenter extends BasePresenter<Model,View>{

        public abstract void getFoundImageBeen(Map<String ,String> map);

    }

    //定义model
    interface Model extends BaseModel{

        Observable<FoundImageBeen> getFoundImageBeen(Map<String ,String> map);

    }

    //定义view
    interface  View extends BaseView{

        void returnFoundImageBeen(FoundImageBeen foundImageBeen);

    }

}
