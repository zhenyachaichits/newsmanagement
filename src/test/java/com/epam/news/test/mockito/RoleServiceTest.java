package com.epam.news.test.mockito;

import com.epam.news.domain.Role;
import com.epam.news.domain.User;
import com.epam.news.persistence.RoleDAO;
import com.epam.news.service.impl.RoleServiceImpl;
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

/**
 * Created by Zheny Chaichits on 04.06.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

    private static final Long TEST_ID = 1L;

    @Mock
    private RoleDAO dao;
    @InjectMocks
    private RoleServiceImpl service;

    @BeforeMethod
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() throws Exception {
        when(dao.all()).thenReturn(new ArrayList<>());
        List<Role> result = service.all();

        assertNotNull(result);
    }

    @Test
    public void testFind() throws Exception {
        when(dao.find(TEST_ID)).thenReturn(new Role());
        Role result = service.find(TEST_ID);

        assertNotNull(result);
    }

    @Test
    public void testAdd() throws Exception {
        Role role = new Role();
        when(dao.add(any())).thenReturn(role);
        Role result = service.add(any());

        assertEquals(role, result);
    }

    @Test
    public void testUpdate() throws Exception {
        Role role = new Role();
        when(dao.update(role)).thenReturn(Boolean.TRUE);

        assertTrue(service.update(role));
    }

    @Test
    public void testDelete() throws Exception {
        when(dao.delete(TEST_ID)).thenReturn(Boolean.TRUE);

        assertTrue(service.delete(TEST_ID));
    }

}
