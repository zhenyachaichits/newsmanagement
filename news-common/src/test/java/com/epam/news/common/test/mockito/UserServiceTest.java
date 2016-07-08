package com.epam.news.common.test.mockito;

import com.epam.news.common.domain.User;
import com.epam.news.common.persistence.UserDAO;
import com.epam.news.common.service.impl.UserServiceImpl;
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
public class UserServiceTest {

    private static final Long TEST_ID = 1L;

    @Mock
    private UserDAO dao;
    @InjectMocks
    private UserServiceImpl service;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() throws Exception {
        when(dao.findAll()).thenReturn(new ArrayList<>());
        List<User> result = service.findAll();

        assertNotNull(result);
    }

    @Test
    public void testFind() throws Exception {
        when(dao.find(TEST_ID)).thenReturn(new User());
        User result = service.find(TEST_ID);

        assertNotNull(result);
    }

    @Test
    public void testAdd() throws Exception {
        User user = new User();
        when(dao.add(any())).thenReturn(user);
        User result = service.save(any());

        assertEquals(user, result);
    }

    @Test
    public void testUpdate() throws Exception {
        User user = new User();
        when(dao.update(user)).thenReturn(Boolean.TRUE);

        assertEquals(service.save(user), user);
    }

    @Test
    public void testDelete() throws Exception {
        when(dao.delete(TEST_ID)).thenReturn(Boolean.TRUE);

        assertTrue(service.delete(TEST_ID));
    }

}
