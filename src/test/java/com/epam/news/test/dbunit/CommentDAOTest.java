package com.epam.news.test.dbunit;

import com.epam.news.domain.Comment;
import com.epam.news.persistence.CommentDAO;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@Transactional
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "/data/comment-data.xml", type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = "/data/comment-data.xml", type = DatabaseOperation.DELETE_ALL)
public class CommentDAOTest {
    private static final String TEST_COMMENT_TEXT = "Text";
    private static final long TEST_ID = 2L;
    private static final int TEST_LIST_SIZE = 2;


    private static Timestamp testCreationDate;
    private static Timestamp testDate;

    @Autowired
    private CommentDAO dao;

    @Before
    public void init() throws Exception {
        testCreationDate = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parsedDate = dateFormat.parse("2016-06-07 03:14:07");
        testDate = new java.sql.Timestamp(parsedDate.getTime());
    }

    @Test
    public void testAdd() throws Exception {
        Comment comment = new Comment();
        comment.setNewsId(TEST_ID);
        comment.setCommentText(TEST_COMMENT_TEXT);
        comment.setCreationDate(testCreationDate);

        assertNotNull(dao.add(comment));
    }

    @Test
    public void testUpdate() throws Exception {
        Comment comment = new Comment();
        comment.setCommentId(TEST_ID);
        comment.setNewsId(TEST_ID);
        comment.setCommentText(TEST_COMMENT_TEXT);
        comment.setCreationDate(testCreationDate);

        assertTrue(dao.update(comment));
    }

    @Test
    public void testFind() throws Exception {
        Comment comment = dao.find(TEST_ID);
        assertNotNull(comment);
    }

    @Test
    @ExpectedDatabase(value = "/data/expected/comment-delete-expected.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void testDelete() throws Exception {
        boolean result = dao.delete(TEST_ID);
        assertFalse(result);
    }

    @Test
    public void testAll() throws Exception {
        List<Comment> newsList = dao.findAll();
        assertEquals(TEST_LIST_SIZE, newsList.size());
    }

    @Test
    public void testAddMultipleComments() throws Exception {

        Comment[] comments = new Comment[3];
        for (int i = 0; i < 3; i++) {
            Comment comment = new Comment();
            comment.setCommentId(i + 1);
            comment.setNewsId(TEST_ID);
            comment.setCommentText(TEST_COMMENT_TEXT);
            comment.setCreationDate(testDate);
            comments[i] = comment;
        }

        dao.addComments(comments);
    }
}
