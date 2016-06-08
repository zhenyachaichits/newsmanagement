package com.epam.news.persistence;

import com.epam.news.domain.Author;
import com.epam.news.persistence.exception.DAOException;

import java.util.List;


/**
 * The interface Author dao.
 */
public interface AuthorDAO extends EntityDAO<Long, Author> {
    /**
     * Add news author.
     *
     * @param newsId   the news id
     * @param authorId the author id
     * @throws DAOException the dao exception
     */
    void addNewsAuthor(long newsId, long authorId) throws DAOException;

    /**
     * Gets news authors.
     *
     * @param newsId the news id
     * @return the news authors
     * @throws DAOException the dao exception
     */
    List<Author> getNewsAuthors(long newsId) throws DAOException;
}
