package com.apl.ticket.been;

import java.util.List;

/**
 * Created by Administrator on 2017/4/3 0003.
 */

public class FoundReadBeen {

    private PaginationInfo paginationInfo;

    private String retcode;

    private String retdesc;

    private List<Article> articleList;

    public PaginationInfo getPaginationInfo() {
        return paginationInfo;
    }

    public void setPaginationInfo(PaginationInfo paginationInfo) {
        this.paginationInfo = paginationInfo;
    }

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public String getRetdesc() {
        return retdesc;
    }

    public void setRetdesc(String retdesc) {
        this.retdesc = retdesc;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public static class PaginationInfo{

        private String currentPage;

        private String recordPerPage;

        private String totalPage;

        private String totalRecord;

        private String offset;

        private String limit;

        public String getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(String currentPage) {
            this.currentPage = currentPage;
        }

        public String getRecordPerPage() {
            return recordPerPage;
        }

        public void setRecordPerPage(String recordPerPage) {
            this.recordPerPage = recordPerPage;
        }

        public String getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(String totalPage) {
            this.totalPage = totalPage;
        }

        public String getTotalRecord() {
            return totalRecord;
        }

        public void setTotalRecord(String totalRecord) {
            this.totalRecord = totalRecord;
        }

        public String getOffset() {
            return offset;
        }

        public void setOffset(String offset) {
            this.offset = offset;
        }

        public String getLimit() {
            return limit;
        }

        public void setLimit(String limit) {
            this.limit = limit;
        }
    }

    public static class Article {

        private String id;

        private String title;

        private String picUrl;

        private String longPicUrl;

        private String tag;

        private String clickUrl;

        private String bigPicUrl;

        private String highlight;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getLongPicUrl() {
            return longPicUrl;
        }

        public void setLongPicUrl(String longPicUrl) {
            this.longPicUrl = longPicUrl;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getClickUrl() {
            return clickUrl;
        }

        public void setClickUrl(String clickUrl) {
            this.clickUrl = clickUrl;
        }

        public String getBigPicUrl() {
            return bigPicUrl;
        }

        public void setBigPicUrl(String bigPicUrl) {
            this.bigPicUrl = bigPicUrl;
        }

        public String getHighlight() {
            return highlight;
        }

        public void setHighlight(String highlight) {
            this.highlight = highlight;
        }
    }

}
