package com.epam.news.service;

import com.epam.news.domain.Tag;
import com.epam.news.exception.ServiceException;

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
    long[] addTags(List<Tag> tags) throws ServiceException;

    /**
     * Add news tag.
     *
     * @param newsId the news id
     * @param tagId  the tag id
     * @throws ServiceException the service exception
     */
    void addNewsTags(long newsId, long... tagId) throws ServiceException;

    /**
     * Gets news tags.
     *
     * @param newsId the news id
     * @return the news tags
     * @throws ServiceException the service exception
     */
    List<Tag> getNewsTags(long newsId) throws ServiceException;

    /**
     * Delete news tags.
     *
     * @param newsId the news id
     * @throws ServiceException the service exception
     */
    void deleteNewsTags(long newsId) throws ServiceException;
}
