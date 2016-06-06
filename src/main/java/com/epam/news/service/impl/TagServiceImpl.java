package com.epam.news.service.impl;

import com.epam.news.persistence.TagDAO;
import com.epam.news.persistence.exception.DAOException;
import com.epam.news.domain.Tag;
import com.epam.news.service.TagService;
import com.epam.news.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yauhen_Chaichyts on 5/31/2016.
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDAO dao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Tag add(Tag tag) throws ServiceException {
        try {
            return dao.add(tag);
        } catch (DAOException e) {
            throw new ServiceException("Couldn't execute tag adding service", e);
        }
    }

    @Override
    public Tag find(Long id) throws ServiceException {
        try {
            return dao.find(id);
        } catch (DAOException e) {
            throw new ServiceException("Couldn't execute tag finding service", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Tag tag) throws ServiceException {
        try {
            return dao.update(tag);
        } catch (DAOException e) {
            throw new ServiceException("Couldn't execute tag updating service", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) throws ServiceException {
        try {
            return dao.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Couldn't execute tag deleting service", e);
        }
    }

    @Override
    public List<Tag> all() throws ServiceException {
        try {
            return dao.all();
        } catch (DAOException e) {
            throw new ServiceException("Couldn't execute getting all tags service", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addNewsTag(long newsId, long tagId) throws ServiceException {
        try {
            dao.addNewsTag(newsId, tagId);
        } catch (DAOException e) {
            throw new ServiceException("Couldn't execute adding news tags service", e);
        }
    }

    @Override
    public List<Tag> getNewsTags(long newsId) throws ServiceException {
        try {
            return dao.getNewsTags(newsId);
        } catch (DAOException e) {
            throw new ServiceException("Couldn't execute getting news tags service", e);
        }
    }
}
