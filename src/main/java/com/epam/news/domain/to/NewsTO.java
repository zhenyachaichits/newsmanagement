package com.epam.news.domain.to;

import com.epam.news.domain.Author;
import com.epam.news.domain.News;
import com.epam.news.domain.Tag;

import java.util.Set;

/**
 * Created by Zheny Chaichits on 26.05.2016.
 */
public class NewsTO {
    private News news;
    private Set<Author> authors;
    private Set<Tag> tags;

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
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
