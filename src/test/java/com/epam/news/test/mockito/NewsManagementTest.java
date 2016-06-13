package com.epam.news.test.mockito;

import com.epam.news.domain.Author;
import com.epam.news.domain.News;
import com.epam.news.domain.Tag;
import com.epam.news.domain.to.NewsTO;
import com.epam.news.persistence.AuthorDAO;
import com.epam.news.persistence.NewsDAO;
import com.epam.news.persistence.TagDAO;
import com.epam.news.exception.ServiceException;
import com.epam.news.service.impl.AuthorServiceImpl;
import com.epam.news.service.impl.NewsServiceImpl;
import com.epam.news.service.impl.TagServiceImpl;
import com.epam.news.service.management.impl.NewsManagementImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

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

    @Test(expected = ServiceException.class)
    public void testAddError() throws Exception {
        NewsTO newsTO = new NewsTO();

        newsManagementService.addNewsData(newsTO);
    }

}
