package com.apl.ticket.been.theatre;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class ThreatreBean {
    private String retdesc;
    private List<TicketUnitListBean> ticketUnitList;
    private String msg;
    private String firstDate;
    private CinemaBean cinema;
    private List<MovieListBean>movieList;

    public String getRetdesc() {
        return retdesc;
    }

    public void setRetdesc(String retdesc) {
        this.retdesc = retdesc;
    }

    public List<TicketUnitListBean> getTicketUnitList() {
        return ticketUnitList;
    }

    public void setTicketUnitList(List<TicketUnitListBean> ticketUnitList) {
        this.ticketUnitList = ticketUnitList;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
    }

    public CinemaBean getCinema() {
        return cinema;
    }

    public void setCinema(CinemaBean cinema) {
        this.cinema = cinema;
    }

    public List<MovieListBean> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<MovieListBean> movieList) {
        this.movieList = movieList;
    }

    //    public String getRetdesc() {
//        return retdesc;
//    }
//
//    public List<TicketUnitListBean> getTicketUnitList() {
//        return ticketUnitList;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public String getFirstDate() {
//        return firstDate;
//
//
//   public CinemaBean getCinema() {
//        return cinema;
//    }
//
//    public List<MovieListBean> getMovieList() {
//        return movieList;
//    }

}
