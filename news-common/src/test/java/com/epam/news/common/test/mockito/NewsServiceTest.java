package com.epam.news.common.test.mockito;

import com.epam.news.common.domain.News;
import com.epam.news.common.domain.criteria.NewsSearchCriteria;
import com.epam.news.common.persistence.NewsDAO;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.impl.NewsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NewsServiceTest {

    private static final long TEST_ID = 1L;
    private static final int TEST_COUNT = 1;

    @Mock
    private NewsDAO dao;
    @InjectMocks
    private NewsServiceImpl service;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() throws Exception {
        when(dao.findAll()).thenReturn(new ArrayList<>());
        List<News> result = service.findAll();

        assertNotNull(result);
    }

    @Test
    public void testFind() throws Exception {
        when(dao.find(TEST_ID)).thenReturn(new News());
        News result = service.find(TEST_ID);

        assertNotNull(result);
    }

    @Test
    public void testAdd() throws Exception {
        News news = new News();
        when(dao.add(news)).thenReturn(news);
        News result = service.save(news);

        assertEquals(news, result);
    }

    @Test
    public void testUpdate() throws Exception {
        News news = new News();
        when(dao.update(news)).thenReturn(Boolean.TRUE);

        assertEquals(service.save(news), news);
    }

    @Test
    public void testDelete() throws Exception {
        when(dao.delete(TEST_ID)).thenReturn(Boolean.TRUE);

        assertTrue(service.delete(TEST_ID));
    }

    @Test
    public void testGetCount() throws Exception {
        when(dao.getNewsCount()).thenReturn(TEST_COUNT);

        assertEquals(TEST_COUNT, service.getNewsCount());
    }

    @Test
    public void testGetOrdered() throws Exception {
        when(dao.findAll()).thenReturn(new ArrayList<>());
        List<News> result = service.getNewsOrderedByCommentsNumber();

        assertNotNull(result);
    }

    @Test(expected = ServiceException.class)
    public void testFindByCriteria() throws Exception {
        NewsSearchCriteria criteria = new NewsSearchCriteria();
        criteria.setAuthorIdSet(new HashSet<>());
        criteria.setTagIdSet(new HashSet<>());

        service.getNewsByCriteria(criteria);
    }
}
