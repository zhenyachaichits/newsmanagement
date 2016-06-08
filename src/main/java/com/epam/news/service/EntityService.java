package com.epam.news.service;

import com.epam.news.service.exception.ServiceException;

import java.util.List;


/**
 * The interface Entity service.
 *
 * @param <K> the type parameter
 * @param <T> the type parameter
 */
public interface EntityService<K, T> {
    /**
     * Add t.
     *
     * @param entity the entity
     * @return the t
     * @throws ServiceException the service exception
     */
    T add(T entity) throws ServiceException;

    /**
     * Find t.
     *
     * @param domain the domain
     * @return the t
     * @throws ServiceException the service exception
     */
    T find(K domain) throws ServiceException;

    /**
     * Update boolean.
     *
     * @param entity the entity
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean update(T entity) throws ServiceException;

    /**
     * Delete boolean.
     *
     * @param domain the domain
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean delete(K domain) throws ServiceException;

    /**
     * All list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<T> all() throws ServiceException;
}
