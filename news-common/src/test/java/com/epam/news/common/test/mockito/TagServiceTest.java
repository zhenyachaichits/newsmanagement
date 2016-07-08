package com.epam.news.common.test.mockito;

import com.epam.news.common.domain.Tag;
import com.epam.news.common.persistence.TagDAO;
import com.epam.news.common.service.impl.TagServiceImpl;
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
public class TagServiceTest {

    private static final Long TEST_ID = 1L;

    @Mock
    private TagDAO dao;
    @InjectMocks
    private TagServiceImpl service;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() throws Exception {
        when(dao.findAll()).thenReturn(new ArrayList<>());
        List<Tag> result = service.findAll();

        assertNotNull(result);
    }

    @Test
    public void testFind() throws Exception {
        when(dao.find(TEST_ID)).thenReturn(new Tag());
        Tag result = service.find(TEST_ID);

        assertNotNull(result);
    }

    @Test
    public void testAdd() throws Exception {
        Tag tag = new Tag();
        when(dao.add(any())).thenReturn(tag);
        Tag result = service.save(any());

        assertEquals(tag, result);
    }

    @Test
    public void testUpdate() throws Exception {
        Tag tag = new Tag();
        when(dao.update(tag)).thenReturn(Boolean.TRUE);

        assertEquals(service.save(tag), tag);
    }

    @Test
    public void testDelete() throws Exception {
        when(dao.delete(TEST_ID)).thenReturn(Boolean.TRUE);

        assertTrue(service.delete(TEST_ID));
    }

}
