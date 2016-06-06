package com.epam.news.service;

import com.epam.news.domain.Tag;
import com.epam.news.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Yauhen_Chaichyts on 5/31/2016.
 */
public interface TagService extends EntityService<Long, Tag> {
    void addNewsTag(long newsId, long tagId) throws ServiceException;

    List<Tag> getNewsTags(long newsId) throws ServiceException;
}
