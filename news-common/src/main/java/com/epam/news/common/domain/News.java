package com.epam.news.common.domain;

import java.sql.Timestamp;


/**
 * The type News.
 */
public class News {
    private Long newsId;
    private String title;
    private String shortText;
    private String fullText;
    private Timestamp creationDate;
    private Timestamp modificationDate;

    /**
     * Gets news id.
     *
     * @return the news id
     */
    public Long getNewsId() {
        return newsId;
    }

    /**
     * Sets news id.
     *
     * @param newsId the news id
     */
    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets short text.
     *
     * @return the short text
     */
    public String getShortText() {
        return shortText;
    }

    /**
     * Sets short text.
     *
     * @param shortText the short text
     */
    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    /**
     * Gets full text.
     *
     * @return the full text
     */
    public String getFullText() {
        return fullText;
    }

    /**
     * Sets full text.
     *
     * @param fullText the full text
     */
    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    /**
     * Gets modification date.
     *
     * @return the modification date
     */
    public Timestamp getModificationDate() {
        return modificationDate;
    }

    /**
     * Sets modification date.
     *
     * @param modificationDate the modification date
     */
    public void setModificationDate(Timestamp modificationDate) {
        this.modificationDate = modificationDate;
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

        News news = (News) o;

        if (newsId != null ? !newsId.equals(news.newsId) : news.newsId != null) return false;
        if (title != null ? !title.equals(news.title) : news.title != null) return false;
        if (shortText != null ? !shortText.equals(news.shortText) : news.shortText != null) return false;
        if (fullText != null ? !fullText.equals(news.fullText) : news.fullText != null) return false;
        if (creationDate != null ? !creationDate.equals(news.creationDate) : news.creationDate != null) return false;
        return modificationDate != null ? modificationDate.equals(news.modificationDate) : news.modificationDate == null;

    }

    @Override
    public int hashCode() {
        int result = newsId != null ? newsId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (shortText != null ? shortText.hashCode() : 0);
        result = 31 * result + (fullText != null ? fullText.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (modificationDate != null ? modificationDate.hashCode() : 0);
        return result;
    }
}
