package com.epam.news.service.management.impl;

import com.epam.news.domain.Author;
import com.epam.news.domain.News;
import com.epam.news.domain.Tag;
import com.epam.news.domain.to.NewsTO;
import com.epam.news.exception.ServiceException;
import com.epam.news.service.AuthorService;
import com.epam.news.service.NewsService;
import com.epam.news.service.TagService;
import com.epam.news.service.management.NewsManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


/**
 * The type News management.
 */
@Service
public class NewsManagementImpl implements NewsManagement {

    @Autowired
    private NewsService newsService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private TagService tagService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addNewsData(NewsTO newsData) throws ServiceException {
        if (newsData.getAuthors() == null || newsData.getTags() == null || newsData.getNews() == null) {
            throw new ServiceException("Invalid data");
        }

        News news = newsService.add(newsData.getNews());
        long newsId = news.getNewsId();

        for (Author author : newsData.getAuthors()) {
            author = authorService.add(author);

            authorService.addNewsAuthor(newsId, author.getAuthorId());
        }
        for (Tag tag : newsData.getTags()) {
            tag = tagService.add(tag);
            tagService.addNewsTag(newsId, tag.getTagId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public NewsTO getNewsData(long newsId) throws ServiceException {
        News news = newsService.find(newsId);
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteNewsData(long newsId) throws ServiceException {
        tagService.deleteNewsTags(newsId);
        authorService.deleteNewsAuthors(newsId);
        newsService.delete(newsId);
    }


}
