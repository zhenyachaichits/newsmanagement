package com.epam.news.common.persistence;

import com.epam.news.common.exception.DAOException;

import java.util.List;


/**
 * The interface Entity dao. Describes common behaviour of data access objects.
 *
 * @param <K> the type of domain
 * @param <T> the type of Entity
 */
public interface EntityDAO<K, T> {
    /**
     * Add entity to data source.
     *
     * @param entity the entity
     * @return added entity
     * @throws DAOException the dao exception
     */
    T add(T entity) throws DAOException;

    /**
     * Find entity by domain.
     *
     * @param domain the domain
     * @return found entity
     * @throws DAOException the dao exception
     */
    T find(K domain) throws DAOException;

    /**
     * Update entity.
     *
     * @param entity the entity
     * @return true if operation successfully completed
     * @throws DAOException the dao exception
     */
    boolean update(T entity) throws DAOException;

    /**
     * Delete entity from data source by domain.
     *
     * @param domain the domain
     * @return true if operation successfully completed
     * @throws DAOException the dao exception
     */
    boolean delete(K domain) throws DAOException;

    /**
     * Get findAllNewsData entities.
     *
     * @return the list of entities
     * @throws DAOException the dao exception
     */
    List<T> findAll() throws DAOException;

}
