package com.epam.news.dao;

import com.epam.news.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Yauhen_Chaichyts on 5/27/2016.
 */
public interface EntityDAO<K, T> {
    T add(T entity) throws DAOException;

    T find(K domain) throws DAOException;

    T update(T entity) throws DAOException;

    T delete(K domain) throws DAOException;

    List<T> all() throws DAOException;

}
