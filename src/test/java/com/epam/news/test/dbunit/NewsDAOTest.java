package com.epam.news.test.dbunit;

import com.epam.news.domain.News;
import com.epam.news.domain.criteria.NewsSearchCriteria;
import com.epam.news.persistence.NewsDAO;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@Transactional
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "/data/news-data.xml", type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = "/data/news-data.xml", type = DatabaseOperation.DELETE_ALL)
public class NewsDAOTest {
    private static final String TEST_TITLE = "Test title";
    private static final String TEST_SHORT_TEXT = "test short text";
    private static final String TEST_FULL_TEXT = "test full text";
    private static final long TEST_ID = 2L;
    private static final int TEST_LIST_SIZE = 2;

    private static Timestamp testCreationDate;
    private static Timestamp testModificationDate;

    @Autowired
    private NewsDAO dao;

    @Before
    public void init() {
        testCreationDate = new Timestamp(System.currentTimeMillis());
        testModificationDate = new Timestamp(System.currentTimeMillis());
    }

    @Test
    public void testAdd() throws Exception {
        News news = new News();
        news.setTitle(TEST_TITLE);
        news.setShortText(TEST_SHORT_TEXT);
        news.setFullText(TEST_FULL_TEXT);
        news.setCreationDate(testCreationDate);
        news.setModificationDate(testModificationDate);

        assertNotNull(dao.add(news));
    }

    @Test
    public void testUpdate() throws Exception {
        News news = new News();
        news.setNewsId(TEST_ID);
        news.setTitle(TEST_TITLE);
        news.setShortText(TEST_SHORT_TEXT);
        news.setFullText(TEST_FULL_TEXT);
        news.setCreationDate(testCreationDate);
        news.setModificationDate(testModificationDate);

        assertTrue(dao.update(news));
    }

    @Test
    public void testFind() throws Exception {
        News news = dao.find(TEST_ID);
        assertNotNull(news);
    }

    @Test
    @ExpectedDatabase(value = "/data/expected/news-expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void testDelete() throws Exception {
        boolean result = dao.delete(TEST_ID);
        assertFalse(result);
    }

    @Test
    public void testAll() throws Exception {
        List<News> newsList = dao.findAll();
        assertEquals(TEST_LIST_SIZE, newsList.size());
    }

    @Test
    public void testNewsCount() throws Exception {
        assertEquals(TEST_LIST_SIZE, dao.getNewsCount());
    }

    @Test
    public void testOrderedList() throws Exception {
        List<News> newsList = dao.getNewsOrderedByCommentsNumber();
        assertEquals(TEST_LIST_SIZE, newsList.size());

    }

    @Test
    public void testSearchByCriteria() throws Exception {
        NewsSearchCriteria criteria = new NewsSearchCriteria();
        Set<Long> idSet = new HashSet<>();
        idSet.add(TEST_ID);
        criteria.setAuthorIdSet(idSet);
        criteria.setTagIdSet(idSet);

        List<News> newsList = dao.getNewsByCriteria(criteria);
        assertEquals(TEST_LIST_SIZE, newsList.size());
    }

    @Test
    public void testSearchByAuthors() throws Exception {
        Set<Long> idSet = new HashSet<>();
        idSet.add(TEST_ID);

        List<News> newsList = dao.getNewsByAuthors(idSet);
        assertEquals(TEST_LIST_SIZE, newsList.size());
    }

    @Test
    public void testSearchByTags() throws Exception {
        Set<Long> idSet = new HashSet<>();
        idSet.add(TEST_ID);

        List<News> newsList = dao.getNewsByTags(idSet);
        assertEquals(TEST_LIST_SIZE, newsList.size());
    }

}
