package com.epam.news.persistence;

import com.epam.news.domain.Author;
import com.epam.news.persistence.exception.DAOException;

import java.util.List;

/**
 * Created by Yauhen_Chaichyts on 5/31/2016.
 */
public interface AuthorDAO extends EntityDAO<Long, Author> {
    void addNewsAuthor(long newsId, long authorId) throws DAOException;

    List<Author> getNewsAuthors(long newsId) throws DAOException;
}
