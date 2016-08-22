package com.epam.news.common.domain;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The type Author.
 */
public class Author implements Serializable {
    private Long authorId;
    private String authorName;
    private Timestamp expiredDate;

    /**
     * Gets author id.
     *
     * @return the author id
     */
    public Long getAuthorId() {
        return authorId;
    }

    /**
     * Sets author id.
     *
     * @param authorId the author id
     */
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    /**
     * Gets author name.
     *
     * @return the author name
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * Sets author name.
     *
     * @param authorName the author name
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * Gets expired date.
     *
     * @return the expired date
     */
    public Timestamp getExpiredDate() {
        return expiredDate;
    }

    /**
     * Sets expired date.
     *
     * @param expiredDate the expired date
     */
    public void setExpiredDate(Timestamp expiredDate) {
        this.expiredDate = expiredDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (authorId != null ? !authorId.equals(author.authorId) : author.authorId != null) return false;
        if (authorName != null ? !authorName.equals(author.authorName) : author.authorName != null) return false;
        return expiredDate != null ? expiredDate.equals(author.expiredDate) : author.expiredDate == null;

    }

    @Override
    public int hashCode() {
        int result = authorId != null ? authorId.hashCode() : 0;
        result = 31 * result + (authorName != null ? authorName.hashCode() : 0);
        result = 31 * result + (expiredDate != null ? expiredDate.hashCode() : 0);
        return result;
    }
}
