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
}
