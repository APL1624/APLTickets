package com.apl.ticket.ui.home.presenter;

import android.util.Log;

import com.apl.ticket.been.HomePageBeen;
import com.apl.ticket.ui.home.contract.MovieHotContract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/26 0026.
 */

public class HomePagePresenter extends MovieHotContract.Presenter {

    private static final String TAG =HomePagePresenter.class.getSimpleName() ;

    @Override
    public void getHomePageBeen(final String type, String city) {
        mModel.getHomePageBeen(type,city).subscribe(new Subscriber<HomePageBeen>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onError(e.getCause()+"网络出错啦!");
            }

            @Override
            public void onNext(HomePageBeen homePageBeen) {
                if (type=="0") {

                    mView.returnHomePageBeen(homePageBeen);
                }else{

                    mView.returnHomePageBeen(getNewHomePageBeen(homePageBeen));
                }


            }
        });
    }
    public HomePageBeen getNewHomePageBeen(HomePageBeen homePageBeen){
        List<HomePageBeen.HPData> list = homePageBeen.getList();
        List<HomePageBeen.HPData>newList1;
        List<HomePageBeen.HPData>newList2=new ArrayList<>();


        Collections.sort(list, new Comparator<HomePageBeen.HPData>() {
            @Override
            public int compare(HomePageBeen.HPData hpData, HomePageBeen.HPData t1) {
               int integer = Integer.parseInt(t1.getNotifyCount());
                int integer1 = Integer.parseInt(hpData.getNotifyCount());
                return  integer-integer1;
            }
        });


        for (int i = 0; i <3 ; i++) {
            HomePageBeen.HPData e = list.get(i);
            newList2.add(e);
        }
        newList1=list.subList(3,list.size());
        Collections.sort(newList1, new Comparator<HomePageBeen.HPData>() {
            @Override
            public int compare(HomePageBeen.HPData hpData, HomePageBeen.HPData t1) {
                int integer = Integer.parseInt(hpData.getReleaseDate().replace("-",""));
                int integer1 = Integer.parseInt(t1.getReleaseDate().replace("-",""));
                return integer-integer1;
            }
        });
        newList2.addAll(newList1);
        homePageBeen.setList(newList2);

        return homePageBeen;
    }

}
