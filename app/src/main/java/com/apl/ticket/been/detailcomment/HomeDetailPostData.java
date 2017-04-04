package com.apl.ticket.been.detailcomment;

import java.util.List;

/**
 * 首页详情-最新评论
 */
//TODO
public class HomeDetailPostData {
    private String boardId;
    private String avatarUrl;
    private String id;
    private String showTag;
    private String userId;
    private String nickName;
    private String createTime;
    private String type;
    private String istop;
    private String category;
    private String text;
    private String like;
    private String likeCount;
    private String commentCount;
    private String postId;
    private String url;
    private String urlDesc;
    private String typeId;
    private List<String> imageList;
    private HDPLCPSharePageData sharePage;

    private String followState;


    public String getBoardId() {
        return boardId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getId() {
        return id;
    }

    public String getShowTag() {
        return showTag;
    }

    public String getUserId() {
        return userId;
    }

    public String getNickName() {
        return nickName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getType() {
        return type;
    }

    public String getIstop() {
        return istop;
    }

    public String getCategory() {
        return category;
    }

    public String getText() {
        return text;
    }

    public String getLike() {
        return like;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public String getPostId() {
        return postId;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlDesc() {
        return urlDesc;
    }

    public String getTypeId() {
        return typeId;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public HDPLCPSharePageData getSharePage() {
        return sharePage;
    }



    public String getFollowState() {
        return followState;
    }

    public static class HDPLCPSharePageData {
        private String thumbnailUrl;
        private String title;
        private String text;
        private String url;

        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public String getTitle() {
            return title;
        }

        public String getText() {
            return text;
        }

        public String getUrl() {
            return url;
        }
    }
}
