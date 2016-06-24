package com.epam.news.common.test.dbunit;

import com.epam.news.common.domain.Role;
import com.epam.news.common.persistence.RoleDAO;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@Transactional
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "/data/role-data.xml", type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = "/data/role-data.xml", type = DatabaseOperation.DELETE_ALL)
public class RoleDAOTest {
    private static final String TEST_ROLE_NAME = "Test";
    private static final long TEST_ID = 2L;
    private static final int TEST_LIST_SIZE = 2;

    @Autowired
    private RoleDAO dao;

    @Test
    public void testAdd() throws Exception {
        Role role = new Role();
        role.setUserId(TEST_ID);
        role.setRoleName(TEST_ROLE_NAME);

        assertEquals(role, dao.add(role));
    }

    @Test
    public void testUpdate() throws Exception {
        Role role = new Role();
        role.setUserId(TEST_ID);
        role.setRoleName(TEST_ROLE_NAME);

        assertTrue(dao.update(role));
    }

    @Test
    public void testFind() throws Exception {
        Role role = dao.find(TEST_ID);
        assertNotNull(role);
    }

    @Test
    @ExpectedDatabase(value = "/data/expected/role-expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void testDelete() throws Exception {
        boolean result = dao.delete(TEST_ID);
        assertFalse(result);
    }

    @Test
    public void testAll() throws Exception {
        List<Role> roleList = dao.findAll();
        assertEquals(TEST_LIST_SIZE, roleList.size());
    }
}
