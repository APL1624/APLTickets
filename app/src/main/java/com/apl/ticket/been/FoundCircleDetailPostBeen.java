package com.apl.ticket.been;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class FoundCircleDetailPostBeen {

    private String result;

    private List<Post> posts;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public static class Post{

        private String boardId;

        private String avatarUrl;

        private String id;

        private String showTag;

        private String userId;

        private String nickName;

        private String createTime;

        //!!!
        private String type;

        private String isStop;

        private String category;

        private String text;

        private String likeCount;

        private String commentCount;

        private String postId;

        private String typeId;

        private List<Image> imageList;

        public String getBoardId() {
            return boardId;
        }

        public void setBoardId(String boardId) {
            this.boardId = boardId;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShowTag() {
            return showTag;
        }

        public void setShowTag(String showTag) {
            this.showTag = showTag;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIsStop() {
            return isStop;
        }

        public void setIsStop(String isStop) {
            this.isStop = isStop;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(String likeCount) {
            this.likeCount = likeCount;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getPostId() {
            return postId;
        }

        public void setPostId(String postId) {
            this.postId = postId;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public List<Image> getImageList() {
            return imageList;
        }

        public void setImageList(List<Image> imageList) {
            this.imageList = imageList;
        }

    }

    public static class Image{

        //缩略图
        private String thumbnailUrl;

        private String thumbnailWidth;

        private String thumbnailHeight;//缩略图的高度

        //原图
        private String originalUrl;

        private String originalWidth;

        private String originalHeight;

        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public void setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
        }

        public String getThumbnailWidth() {
            return thumbnailWidth;
        }

        public void setThumbnailWidth(String thumbnailWidth) {
            this.thumbnailWidth = thumbnailWidth;
        }

        public String getThumbnailHeight() {
            return thumbnailHeight;
        }

        public void setThumbnailHeight(String thumbnailHeight) {
            this.thumbnailHeight = thumbnailHeight;
        }

        public String getOriginalUrl() {
            return originalUrl;
        }

        public void setOriginalUrl(String originalUrl) {
            this.originalUrl = originalUrl;
        }

        public String getOriginalWidth() {
            return originalWidth;
        }

        public void setOriginalWidth(String originalWidth) {
            this.originalWidth = originalWidth;
        }

        public String getOriginalHeight() {
            return originalHeight;
        }

        public void setOriginalHeight(String originalHeight) {
            this.originalHeight = originalHeight;
        }
    }

}
