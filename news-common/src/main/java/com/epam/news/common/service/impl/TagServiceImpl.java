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
     * @return added tag with tagId
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Tag save(Tag tag) throws ServiceException {
        try {
            if(tag.getTagId() == null) {
                return dao.add(tag);
            } else if (!dao.update(tag)){
                throw new ServiceException("Couldn't update data");
            }

            return tag;
        } catch (DAOException e) {
            LOG.error("Error in method: save(Tag tag)", e);
            throw new ServiceException("Couldn't execute tag adding service", e);
        }
    }

    /**
     * Search tag by tagId
     *
     * @param id tag tagId
     * @return found tag
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    public Tag find(Long id) throws ServiceException {
        try {
            return dao.find(id);
        } catch (DAOException e) {
            LOG.error("Error in method: find(Long tagId)", e);
            throw new ServiceException("Couldn't execute tag finding service", e);
        }
    }

    /**
     * Delete tag by tagId
     *
     * @param tagId tag tagId
     * @return
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long tagId) throws ServiceException {
        try {
            dao.deleteNewsTagsByTagId(tagId);
            return dao.delete(tagId);
        } catch (DAOException e) {
            LOG.error("Error in method: delete(Long tagId)", e);
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
     * @return generated tagId array
     * @throws ServiceException the service exception
     */
    @Override
    public Long[] addTags(List<Tag> tags) throws ServiceException {
        try {
            return dao.addTags(tags);
        } catch (DAOException e) {
            LOG.error("Error in method: addTags(List<Tag> tags)", e);
            throw new ServiceException("Couldn't save multiple tags", e);
        }
    }

    /**
     * Add new tag for news entry
     *
     * @param newsId the news tagId
     * @param tagIdList  the tag tagId
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addNewsTags(Long newsId, List<Long> tagIdList) throws ServiceException {
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
     * @param newsId the news tagId
     * @return list of found tags
     * @throws ServiceException if DAOException was thrown
     */
    @Override
    public List<Tag> getNewsTags(Long newsId) throws ServiceException {
        try {
            return dao.getNewsTags(newsId);
        } catch (DAOException e) {
            LOG.error("Error in method: getNewsAuthors(long newsId)", e);
            throw new ServiceException("Couldn't execute getting news tags service", e);
        }
    }

    /**
     * Delete news tags.
     *
     * @param newsId the news tagId
     * @throws ServiceException the service exception
     */
    @Override
    public void deleteNewsTags(Long... newsId) throws ServiceException {
        try {
            dao.deleteNewsTagsByNewsId(newsId);
        } catch (DAOException e) {
            LOG.error("Error in method deleteNewsTagsByNewsId(long newsId)", e);
            throw new ServiceException("Couldn't execute deleting news tags service", e);
        }
    }

    /**
     * Gets news tags.
     *
     * @param newsId the news tagId
     * @return the news tags
     * @throws ServiceException the service exception
     */
    @Override
    public List<Long> getNewsTagIds(Long newsId) throws ServiceException {
        try {
            return dao.getNewsTagIds(newsId);
        } catch (DAOException e) {
            LOG.error("Error in method: getNewsAuthorIds(long newsId)", e);
            throw new ServiceException("Couldn't execute getting news tags service", e);
        }
    }
}
