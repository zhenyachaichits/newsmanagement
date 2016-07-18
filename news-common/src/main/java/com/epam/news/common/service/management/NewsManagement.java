package com.epam.news.common.service.management;

import com.epam.news.common.domain.Author;
import com.epam.news.common.domain.Tag;
import com.epam.news.common.domain.criteria.NewsSearchCriteria;
import com.epam.news.common.domain.to.NewsDetailsTO;
import com.epam.news.common.domain.to.NewsTO;
import com.epam.news.common.exception.ServiceException;

import java.util.List;

/**
 * Created by Yauhen_Chaichyts on 6/3/2016.
 */
public interface NewsManagement {
    void addNewsData(NewsTO newsData) throws ServiceException;

    NewsTO getNewsData(Long newsId) throws ServiceException;

    NewsDetailsTO getNewsDetails(Long newsId) throws ServiceException;

    List<NewsDetailsTO> findAllNewsData() throws ServiceException;

    List<NewsDetailsTO> getNewsForPage(NewsSearchCriteria criteria, int pageNumber) throws ServiceException;

    int getPagesCount() throws ServiceException;

    void deleteNewsData(Long... newsIds) throws ServiceException;

    List<Author> getAllAuthors() throws ServiceException;

    List<Tag> getAllTags() throws ServiceException;
}
