package com.apl.ticket.ui.home.contract;

import com.apl.ticket.been.FoundReadBeen;
import com.apl.ticket.been.FoundReadTabBeen;
import com.vittaw.mvplibrary.base.BaseModel;
import com.vittaw.mvplibrary.base.BasePresenter;
import com.vittaw.mvplibrary.base.BaseView;

import java.util.Map;

import rx.Observable;

import com.vittaw.mvplibrary.base.BaseModel;
import com.vittaw.mvplibrary.base.BasePresenter;

import java.util.Map;

/**
 * Created by Administrator on 2017/3/31.
 */

public interface FoundReadContract {

    abstract class Presenter extends BasePresenter<Model,View> {

        public abstract void getFoundReadTabBeen();

    }

    interface Model extends BaseModel{

        Observable<FoundReadTabBeen> getFoundReadTabBeen();

    }

    interface View extends BaseView{

        void returnFoundTabReadBeen(FoundReadTabBeen foundReadTabBeen);

    }

}
