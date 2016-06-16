package com.epam.news.common.service.management.impl;

import com.epam.news.common.domain.Author;
import com.epam.news.common.domain.News;
import com.epam.news.common.domain.Tag;
import com.epam.news.common.domain.to.NewsTO;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.AuthorService;
import com.epam.news.common.service.NewsService;
import com.epam.news.common.service.TagService;
import com.epam.news.common.service.management.NewsManagement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * The type News management.
 */
@Service
public class NewsManagementImpl implements NewsManagement {

    private static final Logger LOG = LogManager.getLogger(NewsManagementImpl.class);

    @Autowired
    private NewsService newsService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private TagService tagService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addNewsData(NewsTO newsData) throws ServiceException {
        try {
            if (newsData.getAuthors() == null || newsData.getTags() == null || newsData.getNews() == null) {
                throw new ServiceException("Invalid data");
            }

            News news = newsService.add(newsData.getNews());
            long newsId = news.getNewsId();

            long[] authorIdArray = authorService.addAuthors(newsData.getAuthors());
            authorService.addNewsAuthors(newsId, authorIdArray);

            long[] tagIdArray = tagService.addTags(newsData.getTags());
            tagService.addNewsTags(newsId, tagIdArray);
        } catch (ServiceException e) {
            LOG.error("Error in method: addNewsData(NewsTO newsData)", e);
            throw new ServiceException("Couldn't add news data by one transaction", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public NewsTO getNewsData(long newsId) throws ServiceException {
        try {
            News news = newsService.find(newsId);
            List<Author> authors = authorService.getNewsAuthors(newsId);
            List<Tag> tags = tagService.getNewsTags(newsId);

            NewsTO newsData = new NewsTO();
            newsData.setNews(news);
            newsData.setAuthors(authors);
            newsData.setTags(tags);

            return newsData;
        } catch (ServiceException e) {
            LOG.error("Error in method: getNewsData(long newsId)", e);
            throw new ServiceException("Couldn't get news data by one transaction", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteNewsData(long newsId) throws ServiceException {
        try {
            tagService.deleteNewsTags(newsId);
            authorService.deleteNewsAuthors(newsId);
            newsService.delete(newsId);
        } catch (ServiceException e) {
            LOG.error("Error in method: deleteNewsData(long newsId)", e);
            throw new ServiceException("Couldn't delete news data by one transaction", e);
        }
    }


}
