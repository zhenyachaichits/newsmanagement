package com.epam.news.service.impl;

import com.epam.news.persistence.TagDAO;
import com.epam.news.persistence.exception.DAOException;
import com.epam.news.domain.Tag;
import com.epam.news.service.TagService;
import com.epam.news.service.exception.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * The Tag service.
 */
@Service
public class TagServiceImpl implements TagService {

    private static final Logger LOG = Logger.getLogger(TagServiceImpl.class);

    @Autowired
    private TagDAO dao;

    /**
     * Add new tag
     *
     * @param tag tag data
     * @return added tag with id
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Tag add(Tag tag) throws ServiceException {
        try {
            return dao.add(tag);
        } catch (DAOException e) {
            LOG.error("Error in method: add(Tag tag)", e);
            throw new ServiceException("Couldn't execute tag adding service", e);
        }
    }

    /**
     * Search tag by id
     *
     * @param id tag id
     * @return found tag
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    public Tag find(Long id) throws ServiceException {
        try {
            return dao.find(id);
        } catch (DAOException e) {
            LOG.error("Error in method: find(Long id)", e);
            throw new ServiceException("Couldn't execute tag finding service", e);
        }
    }

    /**
     * Update tag data
     *
     * @param tag tag data
     * @return true in case of success
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Tag tag) throws ServiceException {
        try {
            return dao.update(tag);
        } catch (DAOException e) {
            LOG.error("Error in method: update(Tag tag)", e);
            throw new ServiceException("Couldn't execute tag updating service", e);
        }
    }

    /**
     * Delete tag by id
     *
     * @param id tag id
     * @return
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) throws ServiceException {
        try {
            return dao.delete(id);
        } catch (DAOException e) {
            LOG.error("Error in method: delete(Long id)", e);
            throw new ServiceException("Couldn't execute tag deleting service", e);
        }
    }

    /**
     * Get all tags
     *
     * @return list of all tags
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    public List<Tag> all() throws ServiceException {
        try {
            return dao.all();
        } catch (DAOException e) {
            LOG.error("Error in method: all()", e);
            throw new ServiceException("Couldn't execute getting all tags service", e);
        }
    }

    /**
     * Add new tag for news entry
     *
     * @param newsId the news id
     * @param tagId  the tag id
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addNewsTag(long newsId, long tagId) throws ServiceException {
        try {
            dao.addNewsTag(newsId, tagId);
        } catch (DAOException e) {
            LOG.error("Error in method: addNewsTag(long newsId, long tagId)", e);
            throw new ServiceException("Couldn't execute adding news tags service", e);
        }
    }

    /**
     * Search tags for specified news entry
     *
     * @param newsId the news id
     * @return list of found tags
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    public List<Tag> getNewsTags(long newsId) throws ServiceException {
        try {
            return dao.getNewsTags(newsId);
        } catch (DAOException e) {
            LOG.error("Error in method: getNewsTags(long newsId)", e);
            throw new ServiceException("Couldn't execute getting news tags service", e);
        }
    }
}
