package com.apl.ticket.been.detailcomment;

/**
 * 首页详情-最新评论
 */
public class HomeDetailBoardData {
    private String boardName;
    private String boardId;
    private int peopleNum;
    private int postsNum ;
    private int mainPostsNum;
    private String boardIconUrl;
    private String circle;
    private String desc;


    public String getBoardName() {
        return boardName;
    }

    public String getBoardId() {
        return boardId;
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public int getPostsNum() {
        return postsNum;
    }

    public int getMainPostsNum() {
        return mainPostsNum;
    }

    public String getBoardIconUrl() {
        return boardIconUrl;
    }

    public String getCircle() {
        return circle;
    }

    public String getDesc() {
        return desc;
    }
}
