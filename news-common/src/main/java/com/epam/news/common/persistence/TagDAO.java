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
    Long[] addTags(List<Tag> tags) throws DAOException;

    /**
     * Add new news' tag.
     *
     * @param newsId    the news id
     * @param tagIdList the tag id
     * @throws DAOException the dao exception
     */
    void addNewsTags(Long newsId, List<Long> tagIdList) throws DAOException;

    /**
     * Gets findAllNewsData news' tags.
     *
     * @param newsId the news id
     * @return the list of tags
     * @throws DAOException the dao exception
     */
    List<Tag> getNewsTags(Long newsId) throws DAOException;

    /**
     * Gets news tags.
     *
     * @param newsId the news id
     * @return the news tags
     * @throws DAOException the dao exception
     */
    List<Long> getNewsTagIds(Long newsId) throws DAOException;

    /**
     * Delete news tags.
     *
     * @param newsId the news id
     * @throws DAOException the dao exception
     */
    void deleteNewsTagsByNewsId(Long... newsId) throws DAOException;

    /**
     * Delete news tags.
     *
     * @param tagId the news id
     * @throws DAOException the dao exception
     */
    void deleteNewsTagsByTagId(Long tagId) throws DAOException;

}
