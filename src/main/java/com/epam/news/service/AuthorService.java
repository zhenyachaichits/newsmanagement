package com.epam.news.service;

import com.epam.news.domain.Author;
import com.epam.news.exception.ServiceException;

import java.util.List;


/**
 * The interface Author service.
 */
public interface AuthorService extends EntityService<Long, Author> {

    /**
     * Add authors array.
     *
     * @param authors the authors
     * @return generated id array
     * @throws ServiceException the service exception
     */
    long[] addAuthors(List<Author> authors) throws ServiceException;

    /**
     * Add news author.
     *
     * @param newsId   the news id
     * @param authorId the author id array
     * @throws ServiceException the service exception
     */
    void addNewsAuthors(long newsId, long... authorId) throws ServiceException;

    /**
     * Gets news authors list.
     *
     * @param newsId the news id
     * @return the news authors
     * @throws ServiceException the service exception
     */
    List<Author> getNewsAuthors(long newsId) throws ServiceException;

    /**
     * Delete news authors.
     *
     * @param newsId the news id
     * @throws ServiceException the service exception
     */
    void deleteNewsAuthors(long newsId) throws ServiceException;
}
