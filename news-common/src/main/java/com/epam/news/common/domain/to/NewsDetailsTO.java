package com.epam.news.common.domain.to;

import com.epam.news.common.domain.Author;
import com.epam.news.common.domain.Comment;
import com.epam.news.common.domain.News;
import com.epam.news.common.domain.Tag;

import javax.validation.Valid;
import java.util.List;


/**
 * The type News transfer object.
 * Contains findAllNewsData the information about news entry, its authors and tags.
 */
public class NewsDetailsTO {
    private News news;
    private List<Author> authors;
    private List<Tag> tags;
    private List<Comment> comments;
    private long nextNewsId;
    private long previousNewsId;

    /**
     * Gets news.
     *
     * @return the news
     */
    public News getNews() {
        return news;
    }

    /**
     * Sets news.
     *
     * @param news the news
     */
    public void setNews(News news) {
        this.news = news;
    }

    /**
     * Gets authors.
     *
     * @return the authors
     */
    public List<Author> getAuthors() {
        return authors;
    }

    /**
     * Sets authors.
     *
     * @param authors the authors
     */
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    /**
     * Gets tags.
     *
     * @return the tags
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * Sets tags.
     *
     * @param tags the tags
     */
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    /**
     * Gets comments.
     *
     * @return the comments
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Sets comments.
     *
     * @param comments the comments
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * Gets next news id.
     *
     * @return the next news id
     */
    public long getNextNewsId() {
        return nextNewsId;
    }

    /**
     * Sets next news id.
     *
     * @param nextNewsId the next news id
     */
    public void setNextNewsId(long nextNewsId) {
        this.nextNewsId = nextNewsId;
    }

    /**
     * Gets previous news id.
     *
     * @return the previous news id
     */
    public long getPreviousNewsId() {
        return previousNewsId;
    }

    /**
     * Sets previous news id.
     *
     * @param previousNewsId the previous news id
     */
    public void setPreviousNewsId(long previousNewsId) {
        this.previousNewsId = previousNewsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsDetailsTO that = (NewsDetailsTO) o;

        if (nextNewsId != that.nextNewsId) return false;
        if (previousNewsId != that.previousNewsId) return false;
        if (news != null ? !news.equals(that.news) : that.news != null) return false;
        if (authors != null ? !authors.equals(that.authors) : that.authors != null) return false;
        if (tags != null ? !tags.equals(that.tags) : that.tags != null) return false;
        return comments != null ? comments.equals(that.comments) : that.comments == null;

    }

    @Override
    public int hashCode() {
        int result = news != null ? news.hashCode() : 0;
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (int) (nextNewsId ^ (nextNewsId >>> 32));
        result = 31 * result + (int) (previousNewsId ^ (previousNewsId >>> 32));
        return result;
    }
}
