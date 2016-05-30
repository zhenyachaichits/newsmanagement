package com.epam.news.service;

import com.epam.news.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Yauhen_Chaichyts on 5/30/2016.
 */
public interface EntityService<K, T> {
    T add(T entity) throws ServiceException;

    T find(K domain) throws ServiceException;

    boolean update(T entity) throws ServiceException;

    boolean delete(K domain) throws ServiceException;

    List<T> all() throws ServiceException;
}
