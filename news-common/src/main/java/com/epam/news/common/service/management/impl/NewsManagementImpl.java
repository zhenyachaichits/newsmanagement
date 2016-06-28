package com.epam.news.common.service.management.impl;

import com.epam.news.common.domain.Author;
import com.epam.news.common.domain.Comment;
import com.epam.news.common.domain.News;
import com.epam.news.common.domain.Tag;
import com.epam.news.common.domain.to.NewsDetailsTO;
import com.epam.news.common.domain.to.NewsTO;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.AuthorService;
import com.epam.news.common.service.CommentService;
import com.epam.news.common.service.NewsService;
import com.epam.news.common.service.TagService;
import com.epam.news.common.service.management.NewsManagement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Autowired
    private CommentService commentService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addNewsData(NewsTO newsData) throws ServiceException {
        try {
            News news = newsService.add(newsData.getNews());
            long newsId = news.getNewsId();

            authorService.addNewsAuthors(newsId, newsData.getAuthorIdList());
            tagService.addNewsTags(newsId, newsData.getTagIdList());
        } catch (Exception e) {
            LOG.error("Error in method: addNewsData(NewsDetailsTO newsData)", e);
            throw new ServiceException("Couldn't add news data by one transaction", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public NewsDetailsTO getNewsData(long newsId) throws ServiceException {
        try {
            News news = newsService.find(newsId);

            return fillInNewsDetails(news);
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

    @Override
    public List<NewsDetailsTO> findAllNewsData() throws ServiceException {
        try {
            List<News> allNews = newsService.findAll();
            List<NewsDetailsTO> newsDetailsList = new ArrayList<>(allNews.size());

            for (News news : allNews) {
                NewsDetailsTO newsDetails = fillInNewsDetails(news);
                newsDetailsList.add(newsDetails);
            }

            return newsDetailsList;
        } catch (ServiceException e) {
            LOG.error("Error in method: findAllNewsData()", e);
            throw new ServiceException("Couldn't find all news data by one transaction", e);
        }
    }

    private NewsDetailsTO fillInNewsDetails(News news) throws ServiceException {
        long newsId = news.getNewsId();
        List<Author> authors = authorService.getNewsAuthors(newsId);
        List<Tag> tags = tagService.getNewsTags(newsId);
        List<Comment> comments = commentService.getNewsComments(newsId);
        long nextNewsId = newsService.getNextNews(newsId);
        long previousNewsId = newsService.getPreviousNews(newsId);

        NewsDetailsTO newsData = new NewsDetailsTO();
        newsData.setNews(news);
        newsData.setAuthors(authors);
        newsData.setTags(tags);
        newsData.setComments(comments);
        newsData.setNextNewsId(nextNewsId);
        newsData.setPreviousNewsId(previousNewsId);

        return newsData;
    }


}
