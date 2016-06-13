package com.epam.news.test.dbunit;

import com.epam.news.domain.Author;
import com.epam.news.persistence.AuthorDAO;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Before;
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
    private static final long TEST_ID = 2L;
    private static final int TEST_LIST_SIZE = 2;
    private static final long[] TEST_AUTHORS_ARRAY = {1, 2};

    private static Timestamp testExpiredDate;

    @Autowired
    private AuthorDAO dao;

    @Before
    public void init() {
        testExpiredDate = new Timestamp(System.currentTimeMillis());
    }

    @Test
    public void testAdd() throws Exception {
        Author author = new Author();
        author.setAuthorName(TEST_AUTHOR_NAME);
        author.setExpiredDate(testExpiredDate);

        assertNotNull(dao.add(author));
    }

    @Test
    public void testUpdate() throws Exception {
        Author author = new Author();
        author.setAuthorId(TEST_ID);
        author.setAuthorName(TEST_AUTHOR_NAME);
        author.setExpiredDate(testExpiredDate);

        assertTrue(dao.update(author));
    }

    @Test
    public void testFind() throws Exception {
        Author author = dao.find(TEST_ID);
        assertNotNull(author);
    }

    @Test
    @ExpectedDatabase(value = "/data/expected/author-delete-expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void testDelete() throws Exception {
        boolean result = dao.delete(TEST_ID);
        assertFalse(result);
    }

    @Test
    public void testAll() throws Exception {
        List<Author> newsList = dao.findAll();
        assertEquals(TEST_LIST_SIZE, newsList.size());
    }

    @Test
    @ExpectedDatabase(value = "/data/expected/author-add-expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void testAddNewsAuthors() throws Exception {
        dao.addNewsAuthors(TEST_ID, TEST_AUTHORS_ARRAY);
    }
}
