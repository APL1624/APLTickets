package com.apl.ticket.ui.home.contract;

import com.apl.ticket.been.FoundReadBeen;
import com.vittaw.mvplibrary.base.BaseModel;
import com.vittaw.mvplibrary.base.BasePresenter;
import com.vittaw.mvplibrary.base.BaseView;

import java.util.Map;

import rx.Observable;

/**
 * Created by Administrator on 2017/4/3 0003.
 */

public interface FoundReadContentContract {

    abstract class Presenter extends BasePresenter<Model,View> {

        public abstract void getFoundReadBeen(Map<String ,String> map);

    }

    interface Model extends BaseModel {

        Observable<FoundReadBeen> getFoundReadBeen(Map<String ,String> map);

    }

    interface View extends BaseView {

        void returnFoundReadBeen(FoundReadBeen foundReadBeen);

    }
}
