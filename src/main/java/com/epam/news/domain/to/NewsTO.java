package com.epam.news.domain.to;

import com.epam.news.domain.Author;
import com.epam.news.domain.News;

import java.util.Set;

/**
 * Created by Zheny Chaichits on 26.05.2016.
 */
public class NewsTO {
    private News news;
    private Set<Author> authors;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsTO newsTO = (NewsTO) o;

        if (news != null ? !news.equals(newsTO.news) : newsTO.news != null) return false;
        return authors != null ? authors.equals(newsTO.authors) : newsTO.authors == null;

    }

    @Override
    public int hashCode() {
        int result = news != null ? news.hashCode() : 0;
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        return result;
    }
}
