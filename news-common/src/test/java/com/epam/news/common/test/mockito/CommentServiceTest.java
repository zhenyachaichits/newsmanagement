package com.epam.news.common.test.mockito;

import com.epam.news.common.domain.Comment;
import com.epam.news.common.persistence.CommentDAO;
import com.epam.news.common.service.impl.CommentServiceImpl;
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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {
    private static final Long TEST_ID = 1L;

    @Mock
    private CommentDAO dao;
    @InjectMocks
    private CommentServiceImpl service;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() throws Exception {
        when(dao.findAll()).thenReturn(new ArrayList<>());
        List<Comment> result = service.findAll();

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
        Comment result = service.save(any());

        assertEquals(comment, result);
    }

    @Test
    public void testUpdate() throws Exception {
        Comment comment = new Comment();
        when(dao.update(comment)).thenReturn(Boolean.TRUE);

        assertEquals(service.save(comment), comment);
    }

    @Test
    public void testDelete() throws Exception {
        when(dao.delete(TEST_ID)).thenReturn(Boolean.TRUE);

        assertTrue(service.delete(TEST_ID));
    }

}
