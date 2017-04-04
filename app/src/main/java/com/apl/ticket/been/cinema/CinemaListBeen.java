package com.apl.ticket.been.cinema;

import java.util.List;

/**
 * 放映影院
 */
//TODO
public class CinemaListBeen {

    private int retcode;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    private String retdesc;
    private List<CinemaBeen> cinemaList;

    public String getRetdesc() {
        return retdesc;
    }

    public List<CinemaBeen> getCinemaList() {
        return cinemaList;
    }
    //private List<SCOffenCinemaList>
    //private List<SCDistrictList>
}
