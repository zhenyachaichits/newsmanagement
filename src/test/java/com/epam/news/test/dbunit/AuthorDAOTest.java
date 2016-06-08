package com.epam.news.test.dbunit;

import com.epam.news.domain.Author;
import com.epam.news.persistence.AuthorDAO;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@Transactional
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "/data/author-data.xml", type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = "/data/author-data.xml", type = DatabaseOperation.DELETE_ALL)
public class AuthorDAOTest {
    private static final String TEST_AUTHOR_NAME = "Author";
    private static final Timestamp TEST_EXPIRED_DATE;
    private static final long TEST_ID = 2L;
    private static final int TEST_LIST_SIZE = 2;

    static {
        TEST_EXPIRED_DATE = new Timestamp(System.currentTimeMillis() / 2);
    }

    @Autowired
    private AuthorDAO dao;

    @Test
    public void testAdd() throws Exception {
        Author author = new Author();
        author.setAuthorName(TEST_AUTHOR_NAME);
        author.setExpiredDate(TEST_EXPIRED_DATE);

        assertNotNull(dao.add(author));
    }

    @Test
    public void testUpdate() throws Exception {
        Author author = new Author();
        author.setAuthorId(TEST_ID);
        author.setAuthorName(TEST_AUTHOR_NAME);
        author.setExpiredDate(TEST_EXPIRED_DATE);

        assertTrue(dao.update(author));
    }

    @Test
    public void testFind() throws Exception {
        Author author = dao.find(TEST_ID);
        assertNotNull(author);
    }

    @Test
    public void testDelete() throws Exception {
        boolean result = dao.delete(TEST_ID);
        assertFalse(result);
    }

    @Test
    public void testAll() throws Exception {
        List<Author> newsList = dao.all();
        assertEquals(TEST_LIST_SIZE, newsList.size());
    }
}
