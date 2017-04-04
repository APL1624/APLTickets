package com.apl.ticket.ui.moviedetail.model;


import com.apl.ticket.api.Api;
import com.apl.ticket.been.HomeDetailBeen;
import com.apl.ticket.been.detailcomment.HomeDetailCommentBeen;
import com.apl.ticket.ui.moviedetail.contract.MovieDetailContract;
import com.vittaw.mvplibrary.event.AndroidIOToMain;

import java.util.Map;

import rx.Observable;

public class MovieDetailModel implements MovieDetailContract.Model {

    @Override
    public Observable<HomeDetailBeen> getHomeDetail(String account_id, String movie_id, String city) {
        return Api.getApiService().getHomeDetailBeen(account_id,movie_id,city).compose(new AndroidIOToMain.IOToMainTransformer<HomeDetailBeen>());
    }

    @Override
    public Observable<HomeDetailCommentBeen> getHomeDetailComment(Map<String, String> map) {
        return Api.getMovieDetailApiService().getHomeDetailCommentBeen(map).compose(new AndroidIOToMain.IOToMainTransformer<HomeDetailCommentBeen>());
    }

}
