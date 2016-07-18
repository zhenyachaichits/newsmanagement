package com.epam.news.common.service;

import com.epam.news.common.domain.Author;
import com.epam.news.common.exception.ServiceException;

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
    Long[] addAuthors(List<Author> authors) throws ServiceException;

    /**
     * Add news author.
     *
     * @param newsId   the news id
     * @param authorIdList the author id array
     * @throws ServiceException the service exception
     */
    void addNewsAuthors(Long newsId, List<Long> authorIdList) throws ServiceException;

    /**
     * Gets news authors list.
     *
     * @param newsId the news id
     * @return the news authors
     * @throws ServiceException the service exception
     */
    List<Author> getNewsAuthors(Long newsId) throws ServiceException;

    /**
     * Gets news tags.
     *
     * @param newsId the news id
     * @return the news tags
     * @throws ServiceException the service exception
     */
    List<Long> getNewsAuthorIds(Long newsId) throws ServiceException;

    /**
     * Delete news authors.
     *
     * @param newsId the news id
     * @throws ServiceException the service exception
     */
    void deleteNewsAuthors(Long... newsId) throws ServiceException;
}
