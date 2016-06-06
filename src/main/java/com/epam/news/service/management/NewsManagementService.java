package com.epam.news.service.management;

import com.epam.news.domain.to.NewsTO;
import com.epam.news.service.exception.ServiceException;

/**
 * Created by Yauhen_Chaichyts on 6/3/2016.
 */
public interface NewsManagementService {
    void addNewsData(NewsTO newsData) throws ServiceException;
}
