package com.apl.ticket.ui.home.EventBus;

import com.apl.ticket.been.FoundCircleBoardBeen;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class FoundEvent {

    public int WHAT;

    public FoundEvent(int WHAT) {
        this.WHAT = WHAT;
    }

    //想要传递的对象
    private FoundCircleBoardBeen.Board board;

    public FoundCircleBoardBeen.Board getBoard() {
        return board;
    }

    public void setBoard(FoundCircleBoardBeen.Board board) {
        this.board = board;
    }

    private String sort;

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
