package com.epam.news.persistence;

import com.epam.news.persistence.exception.DAOException;

import java.util.List;

/**
 * Created by Yauhen_Chaichyts on 5/27/2016.
 */
public interface EntityDAO<K, T> {
    T add(T entity) throws DAOException;

    T find(K domain) throws DAOException;

    boolean update(T entity) throws DAOException;

    boolean delete(K domain) throws DAOException;

    List<T> all() throws DAOException;

}
