package com.epam.news.common.persistence;

import com.epam.news.common.domain.Tag;
import com.epam.news.common.exception.DAOException;

import java.util.List;


/**
 * The interface Tag dao.
 */
public interface TagDAO extends EntityDAO<Long, Tag> {

    /**
     * Add multiple tags.
     *
     * @param tags the tags
     * @return generated id array
     * @throws DAOException the service exception
     */
    long[] addTags(List<Tag> tags) throws DAOException;

    /**
     * Add new news' tag.
     *
     * @param newsId the news id
     * @param tagId  the tag id
     * @throws DAOException the dao exception
     */
    void addNewsTags(long newsId, long... tagId) throws DAOException;

    /**
     * Gets findAll news' tags.
     *
     * @param newsId the news id
     * @return the list of tags
     * @throws DAOException the dao exception
     */
    List<Tag> getNewsTags(long newsId) throws DAOException;

    /**
     * Delete news tags.
     *
     * @param newsId the news id
     * @throws DAOException the dao exception
     */
    void deleteNewsTags(long newsId) throws DAOException;
}