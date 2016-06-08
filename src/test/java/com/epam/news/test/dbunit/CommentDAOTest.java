package com.epam.news.test.dbunit;

import com.epam.news.domain.Comment;
import com.epam.news.persistence.CommentDAO;
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

/**
 * Created by Yauhen_Chaichyts on 6/7/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@Transactional
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "/data/comment-data.xml", type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = "/data/comment-data.xml", type = DatabaseOperation.DELETE_ALL)
public class CommentDAOTest {
    private static final String TEST_COMMENT_TEXT = "test short text";
    private static final Timestamp TEST_CREATION_DATE;
    private static final long TEST_ID = 2L;
    private static final int TEST_LIST_SIZE = 2;

    static {
        TEST_CREATION_DATE = new Timestamp(System.currentTimeMillis());
    }

    @Autowired
    private CommentDAO dao;

    @Test
    public void testAdd() throws Exception {
        Comment comment = new Comment();
        comment.setNewsId(TEST_ID);
        comment.setCommentText(TEST_COMMENT_TEXT);
        comment.setCreationDate(TEST_CREATION_DATE);

        assertNotNull(dao.add(comment));
    }

    @Test
    public void testUpdate() throws Exception {
        Comment comment = new Comment();
        comment.setCommentId(TEST_ID);
        comment.setNewsId(TEST_ID);
        comment.setCommentText(TEST_COMMENT_TEXT);
        comment.setCreationDate(TEST_CREATION_DATE);

        assertTrue(dao.update(comment));
    }

    @Test
    public void testFind() throws Exception {
        Comment comment = dao.find(TEST_ID);
        assertNotNull(comment);
    }

    @Test
    public void testDelete() throws Exception {
        boolean result = dao.delete(TEST_ID);
        assertFalse(result);
    }

    @Test
    public void testAll() throws Exception {
        List<Comment> newsList = dao.all();
        assertEquals(TEST_LIST_SIZE, newsList.size());
    }
}
