package com.apl.ticket.ui.home.EventBus;

import com.apl.ticket.been.HomePageBeen;

/**
 * Created by Administrator on 2017/3/28.
 */

public class MovieHotLargeEvent {

    public final int WHAT;

    private HomePageBeen homePageBeen;

    public MovieHotLargeEvent(int what){
        WHAT =what;
    }

    public HomePageBeen getHomePageBeen() {
        return homePageBeen;
    }

    public void setHomePageBeen(HomePageBeen homePageBeen) {
        this.homePageBeen = homePageBeen;
    }

    private HomePageBeen.HPData hpData;

    public HomePageBeen.HPData getHpData() {
        return hpData;
    }

    public void setHpData(HomePageBeen.HPData hpData) {
        this.hpData = hpData;
    }
}
