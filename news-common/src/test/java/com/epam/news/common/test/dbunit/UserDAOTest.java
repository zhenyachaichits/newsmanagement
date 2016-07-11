package com.epam.news.common.test.dbunit;

import com.epam.news.common.domain.User;
import com.epam.news.common.persistence.UserDAO;
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
@DatabaseSetup(value = "/data/user-data.xml", type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = "/data/user-data.xml", type = DatabaseOperation.DELETE_ALL)
public class UserDAOTest {
    private static final String TEST_USER_NAME = "Test";
    private static final String TEST_LOGIN = "test_user";
    private static final String TEST_FOUND_LOGIN = "test1";
    private static final String TEST_PASSWORD = "test";
    private static final Long TEST_ID = 2L;
    private static final int TEST_LIST_SIZE = 2;

    @Autowired
    private UserDAO dao;

    @Test
    public void testAdd() throws Exception {
        User user = new User();
        user.setUserName(TEST_USER_NAME);
        user.setLogin(TEST_LOGIN);
        user.setPassword(TEST_PASSWORD);
        user = dao.add(user);

        assertNotNull(user);
    }

    @Test
    public void testUpdate() throws Exception {
        User user = new User();
        user.setUserId(TEST_ID);
        user.setUserName(TEST_USER_NAME);
        user.setLogin(TEST_LOGIN);
        user.setPassword(TEST_PASSWORD);

        assertTrue(dao.update(user));
    }

    @Test
    public void testFind() throws Exception {
        User user = dao.find(TEST_ID);

        assertNotNull(user);
    }

    @Test
    @ExpectedDatabase(value = "/data/expected/user-expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void testDelete() throws Exception {
        boolean result = dao.delete(TEST_ID);

        assertFalse(result);
    }

    @Test
    public void testAll() throws Exception {
        List<User> userList = dao.findAll();

        assertEquals(TEST_LIST_SIZE, userList.size());
    }

    @Test
    public void testFindByLogin() throws Exception {
        User user = dao.getUserByLogin(TEST_FOUND_LOGIN);

        assertEquals(TEST_ID, user.getUserId());
    }
}
