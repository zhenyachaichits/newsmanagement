package com.epam.news.persistence;

import com.epam.news.domain.Tag;
import com.epam.news.persistence.exception.DAOException;

import java.util.List;


/**
 * The interface Tag dao.
 */
public interface TagDAO extends EntityDAO<Long, Tag> {
    /**
     * Add news tag.
     *
     * @param newsId the news id
     * @param tagId  the tag id
     * @throws DAOException the dao exception
     */
    void addNewsTag(long newsId, long tagId) throws DAOException;

    /**
     * Gets news tags.
     *
     * @param newsId the news id
     * @return the news tags
     * @throws DAOException the dao exception
     */
    List<Tag> getNewsTags(long newsId) throws DAOException;
}
