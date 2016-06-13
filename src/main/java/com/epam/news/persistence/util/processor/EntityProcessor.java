package com.epam.news.persistence.util.processor;

import com.epam.news.exception.EntityProcessorException;

import java.sql.ResultSet;
import java.util.List;


/**
 * The interface Entity processor.
 *
 * @param <T> the type parameter
 */
public interface EntityProcessor<T> {

    /**
     * Extracts entity of type T from the result set.
     *
     * @param resultSet the result set
     * @return entity of type T
     * @throws EntityProcessorException the entity processor exception in case of error
     */
    T toEntity(ResultSet resultSet) throws EntityProcessorException;

    /**
     * Extracts list of entity of type T from the result set.
     *
     * @param resultSet the result set
     * @return the list of entity
     * @throws EntityProcessorException the entity processor exception  in case of error
     */
    List<T> toEntityList(ResultSet resultSet) throws EntityProcessorException;
}
