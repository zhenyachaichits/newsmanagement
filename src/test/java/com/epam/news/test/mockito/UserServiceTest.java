package com.epam.news.test.mockito;

import com.epam.news.domain.User;
import com.epam.news.persistence.UserDAO;
import com.epam.news.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private static final Long TEST_ID = 1L;

    @Mock
    private UserDAO dao;
    @InjectMocks
    private UserServiceImpl service;

    @BeforeMethod
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() throws Exception {
        when(dao.all()).thenReturn(new ArrayList<>());
        List<User> result = service.all();

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
        User result = service.add(any());

        assertEquals(user, result);
    }

    @Test
    public void testUpdate() throws Exception {
        User user = new User();
        when(dao.update(user)).thenReturn(Boolean.TRUE);

        assertTrue(service.update(user));
    }

    @Test
    public void testDelete() throws Exception {
        when(dao.delete(TEST_ID)).thenReturn(Boolean.TRUE);

        assertTrue(service.delete(TEST_ID));
    }

}
