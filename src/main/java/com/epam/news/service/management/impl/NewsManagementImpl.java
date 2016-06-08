package com.epam.news.service.management.impl;

import com.epam.news.domain.Author;
import com.epam.news.domain.News;
import com.epam.news.domain.Tag;
import com.epam.news.domain.to.NewsTO;
import com.epam.news.service.AuthorService;
import com.epam.news.service.NewsService;
import com.epam.news.service.TagService;
import com.epam.news.service.exception.ServiceException;
import com.epam.news.service.management.NewsManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yauhen_Chaichyts on 6/3/2016.
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

}