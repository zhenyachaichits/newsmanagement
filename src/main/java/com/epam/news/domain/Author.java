package com.epam.news.domain;

import java.sql.Timestamp;

/**
 * Created by Zheny Chaichits on 26.05.2016.
 */
public class Author {
    private long authorId;
    private String authorName;
    private Timestamp expiredDate;

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Timestamp getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Timestamp expiredDate) {
        this.expiredDate = expiredDate;
    }
}
