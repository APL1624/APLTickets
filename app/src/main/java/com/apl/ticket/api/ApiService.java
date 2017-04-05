package com.apl.ticket.api;

import com.apl.ticket.been.CinemaClassifyBeen;
import com.apl.ticket.been.FoundCircleBoardBeen;
import com.apl.ticket.been.FoundCircleDetailPostBeen;
import com.apl.ticket.been.FoundImageBeen;
import com.apl.ticket.been.FoundReadBeen;
import com.apl.ticket.been.FoundReadTabBeen;
import com.apl.ticket.been.FoundImageBeen;
import com.apl.ticket.been.HomeDetailBeen;
import com.apl.ticket.been.HomePageBeen;
import com.apl.ticket.been.SplashBeen;
import com.apl.ticket.been.cinema.CinemaListBeen;
import com.apl.ticket.been.detailcomment.HomeDetailCommentBeen;
import com.apl.ticket.been.theatre.ThreatreBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/24 0024.
 */

public interface ApiService {

    /** 闪屏页面
     * http://piao.163.com
     * /m/movie/startingUp.html
     *
     *
     * ?timestamp=1490071559576&apiVer=21&apilevel=17&mobileType=android&deviceId=133524057653049&channel=netease&ver=4.16.2&city=370300
     */
    @GET("/m/movie/startingUp.html?")
    Observable<SplashBeen> getSplashBeen(@QueryMap Map<String ,String > map);

    /**
     * 首页 : 电影列表normal
     * http://piao.163.com
     * /m/movie/list.html
     * ?type=0&city=370300 (type 0 ,1)
     */
    @GET("/m/movie/list.html")
    Observable<HomePageBeen> getHomePageBeen(@Query("type") String type,@Query("city") String city );

    /**
     * 电影详情
     * http://piao.163.com
     * /m/movie/detail.html
     * ?account_id=&movie_id=48143&city=370300
     */
    @GET("/m/movie/detail.html")
    Observable<HomeDetailBeen> getHomeDetailBeen (@Query("account_id") String account_id,@Query("movie_id") String movie_id,@Query("city") String city);


    @GET("/ws/district/v1/list?")
    Observable<String>getLocationCityList(@Query("key")String key);

    /**首页详情评论
     * http://qz.dianying.163.com
     * /req_getPosts?
     * login_id=93A1FEFE25A4F773F9EE7598460DF9B88B5C4DC1D033E37CBD34A2748A129E68C571DD9B5A63E9C62405FE76B7CFD03A
     * &sinceId=0
     * &sort=time
     * &count=20
     * &req_type=movie
     * &req_id=48143
     * &login_token=
     * &topAttach=true
     * &maxId=0
     * &apiVer=21
     * &apilevel=17
     * &mobileType=android
     * &deviceId=133524057653049
     * &channel=netease
     * &ver=4.16.2
     * &city=370300
     */
    @GET("/req_getPosts")
    Observable<HomeDetailCommentBeen> getHomeDetailCommentBeen(@QueryMap Map<String,String> map);

    /**
     * http://piao.163.com
     * /m/movie/schedule.html
     * ?movie_id=48143&city=110000&apiVer=21&apilevel=17
     */
    @GET("/m/movie/schedule.html")
    Observable<CinemaListBeen> getCinemaListBeen(@QueryMap Map<String,String> map);

    /**
     * http://piao.163.com
     * /m/base_data.html
     * ?city=110000
     */
    @GET("/m/base_data.html")
    Observable<CinemaClassifyBeen> getCinemaClassifyBeen(@QueryMap Map<String,String> map);

    /**
     * http://piao.163.com
     * /m/movie/getBannerList.html
     * ?data=PgvHPcLxglYagkKzYQ9YZgDJpkFiI4p5JR1A64d5cbodKepoGzmb3w%3D%3D
     * &stamp=1490785958671
     * &apiVer=21
     * &apilevel=17
     * &mobileType=android
     * &deviceId=133524057653049
     * &channel=netease
     * &ver=4.16.2
     * &city=110000
     */
    @GET("/m/movie/getBannerList.html")
    Observable<FoundImageBeen> getFoundImageBeen(@QueryMap Map<String ,String> map);


    /**
     * http://qz.dianying.163.com
     * /circle_getBoardList
     */
    @GET("/circle_getBoardList")
    Observable<FoundCircleBoardBeen> getFoundCircleBoardBeen();

    /**
     * http://qz.dianying.163.com
     * /circle_getPosts
     * ?sort=hot&boardId=14
     */
    @GET("/circle_getPosts")
    Observable<FoundCircleDetailPostBeen> getFoundCircleDetailPostBeen(@Query("sort") String sort,@Query("boardId") String boardId,@Query("maxId") String maxId);


    /**
     * http://piao.163.com
     * /m/movie/articleList.html
     * ?recordPerPage=20&tagId=&apiVer=21&apilevel=17&currentPage=1&city=110000
     */
    @GET("/m/movie/articleList.html")
    Observable<FoundReadBeen> getFoundReadBeen(@QueryMap Map<String ,String> map);

    /**
     * http://piao.163.com
     * /m/movie/article_tags.html
     */
    @GET("/m/movie/article_tags.html")
    Observable<FoundReadTabBeen> getFoundReadTabBeen();

    @GET("/m/cinema/schedule.html")
    Observable<ThreatreBean> getThreatreBeen(@QueryMap Map<String ,String> map);

}
