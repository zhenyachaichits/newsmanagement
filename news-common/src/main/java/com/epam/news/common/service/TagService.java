package com.epam.news.common.service;

import com.epam.news.common.domain.Tag;
import com.epam.news.common.exception.ServiceException;

import java.util.List;


/**
 * The interface Tag service.
 */
public interface TagService extends EntityService<Long, Tag> {

    /**
     * Add multiple tags.
     *
     * @param tags the tags
     * @return generated id array
     * @throws ServiceException the service exception
     */
    Long[] addTags(List<Tag> tags) throws ServiceException;

    /**
     * Add news tag.
     *
     * @param newsId the news id
     * @param tagIdList  the tag id
     * @throws ServiceException the service exception
     */
    void addNewsTags(Long newsId, List<Long> tagIdList) throws ServiceException;

    /**
     * Gets news tags.
     *
     * @param newsId the news id
     * @return the news tags
     * @throws ServiceException the service exception
     */
    List<Tag> getNewsTags(Long newsId) throws ServiceException;

    /**
     * Delete news tags.
     *
     * @param newsId the news id
     * @throws ServiceException the service exception
     */
    void deleteNewsTags(Long... newsId) throws ServiceException;

    /**
     * Gets news tags.
     *
     * @param newsId the news id
     * @return the news tags
     * @throws ServiceException the service exception
     */
    List<Long> getNewsTagIds(Long newsId) throws ServiceException;
}
