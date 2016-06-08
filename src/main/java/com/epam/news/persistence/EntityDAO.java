package com.epam.news.persistence;

import com.epam.news.persistence.exception.DAOException;

import java.util.List;


/**
 * The interface Entity dao.
 *
 * @param <K> the type parameter
 * @param <T> the type parameter
 */
public interface EntityDAO<K, T> {
    /**
     * Add t.
     *
     * @param entity the entity
     * @return the t
     * @throws DAOException the dao exception
     */
    T add(T entity) throws DAOException;

    /**
     * Find t.
     *
     * @param domain the domain
     * @return the t
     * @throws DAOException the dao exception
     */
    T find(K domain) throws DAOException;

    /**
     * Update boolean.
     *
     * @param entity the entity
     * @return the boolean
     * @throws DAOException the dao exception
     */
    boolean update(T entity) throws DAOException;

    /**
     * Delete boolean.
     *
     * @param domain the domain
     * @return the boolean
     * @throws DAOException the dao exception
     */
    boolean delete(K domain) throws DAOException;

    /**
     * All list.
     *
     * @return the list
     * @throws DAOException the dao exception
     */
    List<T> all() throws DAOException;

}
