package com.epam.news.test.mockito;

import com.epam.news.domain.Author;
import com.epam.news.domain.News;
import com.epam.news.domain.Tag;
import com.epam.news.domain.to.NewsTO;
import com.epam.news.persistence.AuthorDAO;
import com.epam.news.persistence.NewsDAO;
import com.epam.news.persistence.TagDAO;
import com.epam.news.service.impl.AuthorServiceImpl;
import com.epam.news.service.impl.NewsServiceImpl;
import com.epam.news.service.impl.TagServiceImpl;
import com.epam.news.service.management.impl.NewsManagementServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
/**
 * Created by Zheny Chaichits on 6/5/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class NewsManagementServiceTest {
    private static final Long TEST_ID = 1L;

    @Mock
    private NewsDAO newsDAO;
    @Mock
    private AuthorDAO authorDAO;
    @Mock
    private TagDAO tagDAO;
    @Spy
    private NewsServiceImpl newsService;
    @Spy
    private AuthorServiceImpl authorService;
    @Spy
    private TagServiceImpl tagService;
    @InjectMocks
    private NewsManagementServiceImpl newsManagementService;

    @BeforeMethod
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAdd() throws Exception {
        NewsTO newsTO = new NewsTO();
        News news = new News();
        newsTO.setNews(news);

        Set<Author> authorSet = new HashSet<>();
        Author author = new Author();
        authorSet.add(author);
        newsTO.setAuthors(authorSet);

        Set<Tag> tagSet = new HashSet<>();
        Tag tag = new Tag();
        tagSet.add(tag);
        newsTO.setTags(tagSet);

        when(newsDAO.add(news)).thenReturn(news);
        when(authorDAO.add(author)).thenReturn(author);
        when(tagDAO.add(tag)).thenReturn(tag);

        doReturn(news).when(newsService).add(news);
        doReturn(author).when(authorService).add(author);
        doReturn(tag).when(tagService).add(tag);

        newsManagementService.addNewsData(newsTO);

    }

}
