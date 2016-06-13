package com.epam.news.service;

import com.epam.news.domain.Tag;
import com.epam.news.exception.ServiceException;

import java.util.List;


/**
 * The interface Tag service.
 */
public interface TagService extends EntityService<Long, Tag> {
    /**
     * Add news tag.
     *
     * @param newsId the news id
     * @param tagId  the tag id
     * @throws ServiceException the service exception
     */
    void addNewsTag(long newsId, long tagId) throws ServiceException;

    /**
     * Gets news tags.
     *
     * @param newsId the news id
     * @return the news tags
     * @throws ServiceException the service exception
     */
    List<Tag> getNewsTags(long newsId) throws ServiceException;
}
