package com.epam.news.dao;

import com.epam.news.dao.exception.DAOException;
import com.epam.news.domain.Author;
import com.epam.news.domain.News;

import java.util.List;

/**
 * Created by Yauhen_Chaichyts on 5/27/2016.
 */
public interface NewsDAO extends EntityDAO<Long, News> {
    List<Author> getNewsAuthors(long newsId) throws DAOException;
}
