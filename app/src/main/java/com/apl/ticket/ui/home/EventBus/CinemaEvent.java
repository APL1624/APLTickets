package com.apl.ticket.ui.home.EventBus;

import com.apl.ticket.been.CinemaClassifyBeen;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class CinemaEvent {

    public int WHAT;

    public CinemaEvent(int WHAT) {
        this.WHAT = WHAT;
    }

    //我们想要传递的参数
    private CinemaClassifyBeen cinemaClassifyBeen;
    //提供set get方法


    public CinemaClassifyBeen getCinemaClassifyBeen() {
        return cinemaClassifyBeen;
    }

    public void setCinemaClassifyBeen(CinemaClassifyBeen cinemaClassifyBeen) {
        this.cinemaClassifyBeen = cinemaClassifyBeen;
    }

    private String districtId;

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    private String districtName;

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
