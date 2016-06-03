package com.epam.news.service;

import com.epam.news.domain.News;
import com.epam.news.domain.criteria.NewsSearchCriteria;
import com.epam.news.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Yauhen_Chaichyts on 5/31/2016.
 */
public interface NewsService extends EntityService<Long, News> {

    List<News> getNewsByCriteria(NewsSearchCriteria criteria) throws ServiceException;

    int getNewsCount() throws ServiceException;
}
