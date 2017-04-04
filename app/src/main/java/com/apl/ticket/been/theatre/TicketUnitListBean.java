package com.apl.ticket.been.theatre;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */
public class TicketUnitListBean {
    private String showDate;
    private String type;
    private String beforeMin;
    private String isHaveTicket;
    private List<TicketListBean> ticketList;

    public String getShowDate() {
        return showDate;
    }

    public String getType() {
        return type;
    }

    public String getBeforeMin() {
        return beforeMin;
    }

    public String getIsHaveTicket() {
        return isHaveTicket;
    }

    public List<TicketListBean> getTicketList() {
        return ticketList;
    }
}
