package com.apl.ticket.been;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class FoundCircleBoardBeen  {

    private String resultDesc;

    private List<Board> boards;

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }

    public static class Board{

        private String boardName;

        private String boardId;

        private String peopleNum;

        private String postsNum;

        private String mainPostsNum;

        private String boardIconUrl;

        public String getBoardName() {
            return boardName;
        }

        public void setBoardName(String boardName) {
            this.boardName = boardName;
        }

        public String getBoardId() {
            return boardId;
        }

        public void setBoardId(String boardId) {
            this.boardId = boardId;
        }

        public String getPeopleNum() {
            return peopleNum;
        }

        public void setPeopleNum(String peopleNum) {
            this.peopleNum = peopleNum;
        }

        public String getPostsNum() {
            return postsNum;
        }

        public void setPostsNum(String postsNum) {
            this.postsNum = postsNum;
        }

        public String getMainPostsNum() {
            return mainPostsNum;
        }

        public void setMainPostsNum(String mainPostsNum) {
            this.mainPostsNum = mainPostsNum;
        }

        public String getBoardIconUrl() {
            return boardIconUrl;
        }

        public void setBoardIconUrl(String boardIconUrl) {
            this.boardIconUrl = boardIconUrl;
        }

    }

}
