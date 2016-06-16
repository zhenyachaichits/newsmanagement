package com.epam.news.common.service.management;

import com.epam.news.common.domain.to.NewsTO;
import com.epam.news.common.exception.ServiceException;

/**
 * Created by Yauhen_Chaichyts on 6/3/2016.
 */
public interface NewsManagement {
    void addNewsData(NewsTO newsData) throws ServiceException;

    NewsTO getNewsData(long newsId) throws ServiceException;

    void deleteNewsData(long newsId) throws ServiceException;
}
