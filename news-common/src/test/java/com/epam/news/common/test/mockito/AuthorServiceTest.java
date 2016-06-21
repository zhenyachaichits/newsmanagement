package com.epam.news.common.test.mockito;

import com.epam.news.common.domain.Author;
import com.epam.news.common.persistence.AuthorDAO;
import com.epam.news.common.exception.DAOException;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.impl.AuthorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceTest {

    private static final Long TEST_ID = 1L;

    @Mock
    private AuthorDAO dao;
    @InjectMocks
    private AuthorServiceImpl service;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() throws Exception {
        when(dao.findAll()).thenReturn(new ArrayList<>());
        List<Author> result = service.findAll();

        assertNotNull(result);
    }

    @Test
    public void testFind() throws Exception {
        when(dao.find(TEST_ID)).thenReturn(new Author());
        Author result = service.find(TEST_ID);

        assertNotNull(result);
    }

    @Test
    public void testAdd() throws Exception {
        Author author = new Author();
        when(dao.add(any())).thenReturn(author);
        Author result = service.add(any());

        assertEquals(author, result);
    }

    @Test
    public void testUpdate() throws Exception {
        Author author = new Author();
        when(dao.update(author)).thenReturn(Boolean.TRUE);

        assertTrue(service.update(author));
    }

    @Test
    public void testDelete() throws Exception {
        when(dao.delete(TEST_ID)).thenReturn(Boolean.TRUE);

        assertTrue(service.delete(TEST_ID));
    }

    @Test
    public void testGetNewsAuthors() throws Exception {
        when(dao.getNewsAuthors(TEST_ID)).thenReturn(new ArrayList<>());
        List<Author> result = service.getNewsAuthors(TEST_ID);

        assertNotNull(result);
    }
}
