package com.apl.ticket.ui.moviedetail.presenter;
import com.apl.ticket.been.HomeDetailBeen;
import com.apl.ticket.been.detailcomment.HomeDetailCommentBeen;
import com.apl.ticket.ui.moviedetail.contract.MovieDetailContract;

import java.util.Map;

import rx.Subscriber;


public class MovieDetailPresenter extends MovieDetailContract.Presenter {

    @Override
    public void getHomeDetail(String account_id, String movie_id, String city) {
        mModel.getHomeDetail(account_id,movie_id,city).subscribe(new Subscriber<HomeDetailBeen>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
//                mView.onError(e.getCause().getMessage() + "网络出错了!");
            }

            @Override
            public void onNext(HomeDetailBeen homeDetailBeen) {
                mView.returnHomeDetailBeen(homeDetailBeen);
            }
        });
    }

    @Override
    public void getHomeDetailComment(Map<String, String> map) {
        mModel.getHomeDetailComment(map).subscribe(new Subscriber<HomeDetailCommentBeen>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onError(e.getCause().getMessage()+"网络出错了!");
            }

            @Override
            public void onNext(HomeDetailCommentBeen homeDetailCommentBeen) {
                mView.returnHomeDetailComment(homeDetailCommentBeen);
            }
        });
    }

}
