package com.epam.news.common.service;

import com.epam.news.common.exception.ServiceException;

import java.util.List;


/**
 * The interface Entity service.
 *
 * @param <K> the type of domain
 * @param <T> the type of entity
 */
public interface EntityService<K, T> {
    /**
     * Add entity to data source.
     *
     * @param entity the entity
     * @return added entity
     * @throws ServiceException the dao exception
     */
    T add(T entity) throws ServiceException;

    /**
     * Find entity by domain.
     *
     * @param domain the domain
     * @return found entity
     * @throws ServiceException the dao exception
     */
    T find(K domain) throws ServiceException;

    /**
     * Update entity.
     *
     * @param entity the entity
     * @return true if operation successfully completed
     * @throws ServiceException the dao exception
     */
    boolean update(T entity) throws ServiceException;

    /**
     * Delete entity from data source by domain.
     *
     * @param domain the domain
     * @return true if operation successfully completed
     * @throws ServiceException the dao exception
     */
    boolean delete(K domain) throws ServiceException;

    /**
     * Get findAllNewsData entities.
     *
     * @return the list of entities
     * @throws ServiceException the dao exception
     */
    List<T> findAll() throws ServiceException;
}
