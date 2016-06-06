package com.epam.news.service;

import com.epam.news.domain.Author;
import com.epam.news.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Yauhen_Chaichyts on 5/31/2016.
 */
public interface AuthorService extends EntityService<Long, Author> {
    void addNewsAuthor(long newsId, long authorId) throws ServiceException;

    List<Author> getNewsAuthors(long newsId) throws ServiceException;
}
