package com.epam.news.service;

import com.epam.news.domain.Author;
import com.epam.news.service.exception.ServiceException;

import java.util.List;


/**
 * The interface Author service.
 */
public interface AuthorService extends EntityService<Long, Author> {
    /**
     * Add news author.
     *
     * @param newsId   the news id
     * @param authorId the author id
     * @throws ServiceException the service exception
     */
    void addNewsAuthor(long newsId, long authorId) throws ServiceException;

    /**
     * Gets news authors.
     *
     * @param newsId the news id
     * @return the news authors
     * @throws ServiceException the service exception
     */
    List<Author> getNewsAuthors(long newsId) throws ServiceException;
}
