package com.epam.news.persistence;

import com.epam.news.domain.Author;
import com.epam.news.exception.DAOException;

import java.util.List;


/**
 * The interface Author dao.
 */
public interface AuthorDAO extends EntityDAO<Long, Author> {
    /**
     * Add new news' author data.
     *
     * @param newsId   the news id
     * @param authorId the author id
     * @throws DAOException the dao exception
     */
    void addNewsAuthor(long newsId, long authorId) throws DAOException;

    /**
     * Gets the list of news' authors.
     *
     * @param newsId the news id
     * @return the news authors list
     * @throws DAOException the dao exception
     */
    List<Author> getNewsAuthors(long newsId) throws DAOException;
}
