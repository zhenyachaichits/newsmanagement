package com.epam.news.common.persistence;

import com.epam.news.common.domain.Author;
import com.epam.news.common.exception.DAOException;

import java.util.List;


/**
 * The interface Author dao.
 */
public interface AuthorDAO extends EntityDAO<Long, Author> {


    /**
     * Add authors array.
     *
     * @param authors the authors
     * @return generated id array
     * @throws DAOException the dao exception
     */
    long[] addAuthors(List<Author> authors) throws DAOException;

    /**
     * Add new news author data.
     *
     * @param newsId        the news id
     * @param authorIdArray the author id array
     * @throws DAOException the dao exception
     */
    void addNewsAuthors(long newsId, long... authorIdArray) throws DAOException;

    /**
     * Gets the list of news' authors.
     *
     * @param newsId the news id
     * @return the news authors list
     * @throws DAOException the dao exception
     */
    List<Author> getNewsAuthors(long newsId) throws DAOException;


    /**
     * Delete news authors.
     *
     * @param newsId the news id
     * @throws DAOException the dao exception
     */
    void deleteNewsAuthors(long newsId) throws DAOException;
}
