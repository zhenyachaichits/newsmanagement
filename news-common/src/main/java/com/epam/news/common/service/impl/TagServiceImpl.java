package com.epam.news.common.service.impl;

import com.epam.news.common.persistence.TagDAO;
import com.epam.news.common.service.TagService;
import com.epam.news.common.domain.Tag;
import com.epam.news.common.exception.DAOException;
import com.epam.news.common.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * The Tag service.
 */
@Service
public class TagServiceImpl implements TagService {

    private static final Logger LOG = LogManager.getLogger(TagServiceImpl.class);

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
     * Get findAllNewsData tags
     *
     * @return list of findAllNewsData tags
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    public List<Tag> findAll() throws ServiceException {
        try {
            return dao.findAll();
        } catch (DAOException e) {
            LOG.error("Error in method: findAllNewsData()", e);
            throw new ServiceException("Couldn't execute getting findAllNewsData tags service", e);
        }
    }

    /**
     * Add multiple tags.
     *
     * @param tags the tags
     * @return generated id array
     * @throws ServiceException the service exception
     */
    @Override
    public long[] addTags(List<Tag> tags) throws ServiceException {
        try {
            return dao.addTags(tags);
        } catch (DAOException e) {
            LOG.error("Error in method: addTags(List<Tag> tags)", e);
            throw new ServiceException("Couldn't add multiple tags", e);
        }
    }

    /**
     * Add new tag for news entry
     *
     * @param newsId the news id
     * @param tagIdList  the tag id
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addNewsTags(long newsId, List<Long> tagIdList) throws ServiceException {
        try {
            dao.addNewsTags(newsId, tagIdList);
        } catch (DAOException e) {
            LOG.error("Error in method: addNewsTags(long newsId, long tagIdList)", e);
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

    /**
     * Delete news tags.
     *
     * @param newsId the news id
     * @throws ServiceException the service exception
     */
    @Override
    public void deleteNewsTags(long newsId) throws ServiceException {
        try {
            dao.deleteNewsTags(newsId);
        } catch (DAOException e) {
            LOG.error("Error in method deleteNewsTags(long newsId)", e);
            throw new ServiceException("Couldn't execute deleting news tags service", e);
        }
    }
}
