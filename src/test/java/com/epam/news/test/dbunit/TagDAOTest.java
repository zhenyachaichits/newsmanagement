package com.epam.news.test.dbunit;

import com.epam.news.domain.Tag;
import com.epam.news.persistence.TagDAO;
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
@DatabaseSetup(value = "/data/tag-data.xml", type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = "/data/tag-data.xml", type = DatabaseOperation.DELETE_ALL)
public class TagDAOTest {
    private static final String TEST_TAG_NAME = "Test";
    private static final long TEST_ID = 2L;
    private static final int TEST_LIST_SIZE = 2;

    @Autowired
    private TagDAO dao;

    @Test
    public void testAdd() throws Exception {
        Tag tag = new Tag();
        tag.setTagName(TEST_TAG_NAME);

        tag = dao.add(tag);
        assertNotNull(tag);
    }

    @Test
    public void testUpdate() throws Exception {
        Tag tag = new Tag();
        tag.setTagId(TEST_ID);
        tag.setTagName(TEST_TAG_NAME);

        assertTrue(dao.update(tag));
    }

    @Test
    public void testFind() throws Exception {
        Tag tag = dao.find(TEST_ID);
        assertNotNull(tag);
    }

    @Test
    public void testDelete() throws Exception {
        boolean result = dao.delete(TEST_ID);
        assertFalse(result);
    }

    @Test
    public void testAll() throws Exception {
        List<Tag> tagList = dao.all();
        assertEquals(TEST_LIST_SIZE, tagList.size());
    }
}
