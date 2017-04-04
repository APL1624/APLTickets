package com.apl.ticket.been.detailcomment;

import java.util.List;

/**
 * 首页详情-最新评论
 */

public class HomeDetailCommentBeen {
   private List<HomeDetailPostData> topPosts;
    private List<HomeDetailPostData>posts;
    private String resultDesc;
    private HomeDetailBoardData board;


    public List<HomeDetailPostData> getTopPosts() {
        return topPosts;
    }

    public List<HomeDetailPostData> getPosts() {
        return posts;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public HomeDetailBoardData getBoard() {
        return board;
    }
}
