package com.epam.news.persistence;

import com.epam.news.domain.Tag;
import com.epam.news.persistence.exception.DAOException;

import java.util.List;

/**
 * Created by Yauhen_Chaichyts on 5/31/2016.
 */
public interface TagDAO extends EntityDAO<Long, Tag> {
    void addNewsTag(long newsId, long tagId) throws DAOException;

    List<Tag> getNewsTags(long newsId) throws DAOException;
}
