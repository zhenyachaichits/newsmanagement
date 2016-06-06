package com.epam.news.test.mockito;

import com.epam.news.domain.Comment;
import com.epam.news.persistence.CommentDAO;
import com.epam.news.service.impl.CommentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Zheny Chaichits on 6/5/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {
    private static final Long TEST_ID = 1L;

    @Mock
    private CommentDAO dao;
    @InjectMocks
    private CommentServiceImpl service;

    @BeforeMethod
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() throws Exception {
        when(dao.all()).thenReturn(new ArrayList<>());
        List<Comment> result = service.all();

        assertNotNull(result);
    }

    @Test
    public void testFind() throws Exception {
        when(dao.find(TEST_ID)).thenReturn(new Comment());
        Comment result = service.find(TEST_ID);

        assertNotNull(result);
    }

    @Test
    public void testAdd() throws Exception {
        Comment comment = new Comment();
        when(dao.add(any())).thenReturn(comment);
        Comment result = service.add(any());

        assertEquals(comment, result);
    }

    @Test
    public void testUpdate() throws Exception {
        Comment comment = new Comment();
        when(dao.update(comment)).thenReturn(Boolean.TRUE);

        assertTrue(service.update(comment));
    }

    @Test
    public void testDelete() throws Exception {
        when(dao.delete(TEST_ID)).thenReturn(Boolean.TRUE);

        assertTrue(service.delete(TEST_ID));
    }

}
