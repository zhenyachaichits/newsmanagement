package com.epam.news.domain.to;

import com.epam.news.domain.Comment;
import com.epam.news.domain.News;

/**
 * Created by Zheny Chaichits on 26.05.2016.
 */
public class CommentTO {
    private Comment comment;
    private News news;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
