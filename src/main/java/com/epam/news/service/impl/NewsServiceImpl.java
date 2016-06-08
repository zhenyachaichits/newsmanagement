package com.epam.news.service.impl;

import com.epam.news.domain.News;
import com.epam.news.domain.criteria.NewsSearchCriteria;
import com.epam.news.persistence.NewsDAO;
import com.epam.news.persistence.exception.DAOException;
import com.epam.news.service.NewsService;
import com.epam.news.service.exception.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Yauhen_Chaichyts on 5/31/2016.
 */
@Service
public class NewsServiceImpl implements NewsService {

    private static final Logger LOG = Logger.getLogger(NewsServiceImpl.class);

    @Autowired
    private NewsDAO dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public News add(News news) throws ServiceException {
        try {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            news.setCreationDate(currentTime);
            news.setModificationDate(currentTime);

            return dao.add(news);
        } catch (DAOException e) {
            LOG.error("Error in method: add(News news)", e);
            throw new ServiceException("Couldn't execute news adding service", e);
        }
    }

    @Override
    public News find(Long id) throws ServiceException {
        try {
            return dao.find(id);
        } catch (DAOException e) {
            LOG.error("Error in method: find(Long id)", e);
            throw new ServiceException("Couldn't execute news finding service", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(News news) throws ServiceException {
        try {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            news.setModificationDate(currentTime);

            return dao.update(news);
        } catch (DAOException e) {
            LOG.error("Error in method: update(News news)", e);
            throw new ServiceException("Couldn't execute news updating service", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) throws ServiceException {
        try {
            return dao.delete(id);
        } catch (DAOException e) {
            LOG.error("Error in method: delete(Long id)", e);
            throw new ServiceException("Couldn't execute news deleting service", e);
        }
    }

    @Override
    public List<News> all() throws ServiceException {
        try {
            return dao.all();
        } catch (DAOException e) {
            LOG.error("Error in method:  all()", e);
            throw new ServiceException("Couldn't execute getting all news service", e);
        }
    }

    @Override
    public List<News> getNewsByCriteria(NewsSearchCriteria criteria) throws ServiceException {
        try {
            if (criteria.getAuthorIdSet().isEmpty() && criteria.getTagIdSet().isEmpty()) {
                throw new ServiceException("Criteria is invalid");
            }
            if (criteria.getAuthorIdSet().isEmpty() && !criteria.getTagIdSet().isEmpty()) {
                return dao.getNewsByTags(criteria.getTagIdSet());
            }
            if (!criteria.getAuthorIdSet().isEmpty() && criteria.getTagIdSet().isEmpty()) {
                return dao.getNewsByAuthors(criteria.getAuthorIdSet());
            }
            return dao.getNewsByCriteria(criteria);
        } catch (DAOException | ServiceException e) {
            LOG.error("Error in method: getNewsByCriteria(NewsSearchCriteria criteria)", e);
            throw new ServiceException("Couldn't execute news searching by criteria service", e);
        }
    }

    @Override
    public List<News> getNewsOrderedByCommentsNumber() throws ServiceException {
        try {
            return dao.getNewsOrderedByCommentsNumber();
        } catch (DAOException e) {
            LOG.error("Error in method: getNewsOrderedByCommentsNumber()", e);
            throw new ServiceException("Couldn't execute getting all news ordered by comments service", e);
        }
    }

    @Override
    public int getNewsCount() throws ServiceException {
        try {
            return dao.getNewsCount();
        } catch (DAOException e) {
            LOG.error("Error in method: getNewsCount()", e);
            throw new ServiceException("Couldn't execute news counting service", e);
        }
    }
}
