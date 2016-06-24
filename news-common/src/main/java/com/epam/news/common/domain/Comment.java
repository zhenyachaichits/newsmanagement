package com.epam.news.common.domain;

import java.sql.Timestamp;


/**
 * The type Comment.
 */
public class Comment {
    private long commentId;
    private long newsId;
    private String commentText;
    private Timestamp creationDate;

    /**
     * Gets comment id.
     *
     * @return the comment id
     */
    public long getCommentId() {
        return commentId;
    }

    /**
     * Sets comment id.
     *
     * @param commentId the comment id
     */
    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    /**
     * Gets news id.
     *
     * @return the news id
     */
    public long getNewsId() {
        return newsId;
    }

    /**
     * Sets news id.
     *
     * @param newsId the news id
     */
    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }

    /**
     * Gets comment text.
     *
     * @return the comment text
     */
    public String getCommentText() {
        return commentText;
    }

    /**
     * Sets comment text.
     *
     * @param commentText the comment text
     */
    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    /**
     * Gets creation date.
     *
     * @return the creation date
     */
    public Timestamp getCreationDate() {
        return creationDate;
    }

    /**
     * Sets creation date.
     *
     * @param creationDate the creation date
     */
    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (commentId != comment.commentId) return false;
        if (newsId != comment.newsId) return false;
        if (commentText != null ? !commentText.equals(comment.commentText) : comment.commentText != null) return false;
        return creationDate != null ? creationDate.equals(comment.creationDate) : comment.creationDate == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (commentId ^ (commentId >>> 32));
        result = 31 * result + (int) (newsId ^ (newsId >>> 32));
        result = 31 * result + (commentText != null ? commentText.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }
}
