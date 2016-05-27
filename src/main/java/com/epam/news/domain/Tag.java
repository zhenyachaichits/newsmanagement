package com.epam.news.domain;

/**
 * Created by Zheny Chaichits on 26.05.2016.
 */
public class Tag {
    private long tagId;
    private String tagName;

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
