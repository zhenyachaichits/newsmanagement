package com.epam.news.domain.to;

import com.epam.news.domain.Author;
import com.epam.news.domain.News;
import com.epam.news.domain.Tag;

import java.util.List;
import java.util.Set;


/**
 * The type News transfer object.
 * Contains findAll the information about news entry, its authors and tags.
 */
public class NewsTO {
    private News news;
    private List<Author> authors;
    private List<Tag> tags;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsTO newsTO = (NewsTO) o;

        if (news != null ? !news.equals(newsTO.news) : newsTO.news != null) return false;
        if (authors != null ? !authors.equals(newsTO.authors) : newsTO.authors != null) return false;
        return tags != null ? tags.equals(newsTO.tags) : newsTO.tags == null;

    }

    @Override
    public int hashCode() {
        int result = news != null ? news.hashCode() : 0;
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }
}
