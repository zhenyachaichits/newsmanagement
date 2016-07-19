package com.epam.news.common.service.management.impl;

import com.epam.news.common.domain.Author;
import com.epam.news.common.domain.Comment;
import com.epam.news.common.domain.News;
import com.epam.news.common.domain.Tag;
import com.epam.news.common.domain.criteria.NewsSearchCriteria;
import com.epam.news.common.domain.to.NewsDetailsTO;
import com.epam.news.common.domain.to.NewsTO;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.AuthorService;
import com.epam.news.common.service.CommentService;
import com.epam.news.common.service.NewsService;
import com.epam.news.common.service.TagService;
import com.epam.news.common.service.management.NewsManagement;
import com.epam.news.common.service.util.pagination.NewsPaginationUtil;
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
    @Autowired
    private NewsPaginationUtil paginationUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveNewsData(NewsTO newsData) throws ServiceException {
        try {
            News news = newsService.save(newsData.getNews());
            Long newsId = news.getNewsId();

            if (newsData.getNews().getNewsId() != null) {
                authorService.deleteNewsAuthors(newsId);
                tagService.deleteNewsTags(newsId);
            }

            authorService.addNewsAuthors(newsId, newsData.getAuthorIdList());
            tagService.addNewsTags(newsId, newsData.getTagIdList());
        } catch (Exception e) {
            LOG.error("Error in method: saveNewsData(NewsDetailsTO newsData)", e);
            throw new ServiceException("Couldn't save news data by one transaction", e);
        }
    }

    @Override
    public NewsTO getNewsData(Long newsId) throws ServiceException {
        try {
            News news = newsService.find(newsId);

            return fillInNewsData(news);
        } catch (ServiceException e) {
            LOG.error("Error in method: getNewsDetails(long newsId)", e);
            throw new ServiceException("Couldn't get news data by one transaction", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public NewsDetailsTO getNewsDetails(Long newsId) throws ServiceException {
        try {
            News news = newsService.find(newsId);

            return fillInNewsDetails(news);
        } catch (ServiceException e) {
            LOG.error("Error in method: getNewsDetails(long newsId)", e);
            throw new ServiceException("Couldn't get news data by one transaction", e);
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

    @Override
    public List<NewsDetailsTO> getNewsForPage(NewsSearchCriteria criteria, int pageNumber) throws ServiceException {
        try {
            int newsOnPage = paginationUtil.getNewsOnPageNumber();

            List<News> newsForPage = newsService.getNewsForPage(criteria, pageNumber, newsOnPage);
            List<NewsDetailsTO> newsDetailsList = new ArrayList<>(newsForPage.size());

            for (News news : newsForPage) {
                NewsDetailsTO newsDetails = fillInNewsDetails(news);
                newsDetailsList.add(newsDetails);
            }

            return newsDetailsList;
        } catch (ServiceException e) {
            LOG.error("Error in method: findAllNewsData()", e);
            throw new ServiceException("Couldn't find all news data by one transaction", e);
        }
    }

    @Override
    public int getPagesCount(NewsSearchCriteria criteria) throws ServiceException {
        try {
            int allNewsCount;
            if (criteria.getTagIdSet().isEmpty() && criteria.getAuthorIdSet().isEmpty()) {
                allNewsCount = newsService.getNewsCount();
            } else {
                allNewsCount = newsService.getNewsCount(criteria);
            }

            return paginationUtil.countPages(allNewsCount);
        } catch (ServiceException e) {
            LOG.error("Error in method: getPagesCount()", e);
            throw new ServiceException("Couldn't get pages count", e);
        }
    }

    @Override
    public void deleteNewsData(Long... newsIds) throws ServiceException {
        try {
            authorService.deleteNewsAuthors(newsIds);
            tagService.deleteNewsTags(newsIds);
            commentService.deleteNewsComments(newsIds);
            newsService.deleteNews(newsIds);

        } catch (ServiceException e) {
            LOG.error("Error in method: deleteNewsData(newsIds)", e);
            throw new ServiceException("Couldn't delete news data", e);
        }
    }

    @Override
    public List<Author> getAllAuthors() throws ServiceException {
        return authorService.findAll();
    }

    @Override
    public List<Tag> getAllTags() throws ServiceException {
        return tagService.findAll();
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

    private NewsTO fillInNewsData(News news) throws ServiceException {
        long newsId = news.getNewsId();
        List<Long> authors = authorService.getNewsAuthorIds(newsId);
        List<Long> tags = tagService.getNewsTagIds(newsId);

        NewsTO newsData = new NewsTO();
        newsData.setNews(news);
        newsData.setAuthorIdList(authors);
        newsData.setTagIdList(tags);

        return newsData;
    }


}
