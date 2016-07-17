package com.epam.news.common.test.mockito;

import com.epam.news.common.domain.Author;
import com.epam.news.common.domain.News;
import com.epam.news.common.domain.Tag;
import com.epam.news.common.domain.to.NewsTO;
import com.epam.news.common.persistence.AuthorDAO;
import com.epam.news.common.persistence.NewsDAO;
import com.epam.news.common.persistence.TagDAO;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.impl.AuthorServiceImpl;
import com.epam.news.common.service.impl.NewsServiceImpl;
import com.epam.news.common.service.impl.TagServiceImpl;
import com.epam.news.common.service.management.impl.NewsManagementImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NewsManagementTest {

    @Mock
    private NewsDAO newsDAO;
    @Mock
    private AuthorDAO authorDAO;
    @Mock
    private TagDAO tagDAO;
    @Spy
    @InjectMocks
    private NewsServiceImpl newsService;
    @Spy
    @InjectMocks
    private AuthorServiceImpl authorService;
    @Spy
    @InjectMocks
    private TagServiceImpl tagService;
    @InjectMocks
    private NewsManagementImpl newsManagementService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAdd() throws Exception {
        NewsTO newsTO = new NewsTO();
        News news = new News();
        newsTO.setNews(news);

        when(newsDAO.add(news)).thenReturn(news);

        doReturn(news).when(newsService).save(news);
        newsManagementService.addNewsData(newsTO);
    }

    @Test(expected = ServiceException.class)
    public void testAddError() throws Exception {
        NewsTO newsTO = new NewsTO();

        newsManagementService.addNewsData(newsTO);
    }

}
