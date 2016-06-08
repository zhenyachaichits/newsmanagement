package com.epam.news.persistence.util.processor;

import com.epam.news.persistence.util.processor.exception.EntityProcessorException;

import java.sql.ResultSet;
import java.util.List;


/**
 * The interface Entity processor.
 *
 * @param <T> the type parameter
 */
public interface EntityProcessor<T> {

    /**
     * To entity t.
     *
     * @param resultSet the result set
     * @return the t
     * @throws EntityProcessorException the entity processor exception
     */
    T toEntity(ResultSet resultSet) throws EntityProcessorException;

    /**
     * To entity list list.
     *
     * @param resultSet the result set
     * @return the list
     * @throws EntityProcessorException the entity processor exception
     */
    List<T> toEntityList(ResultSet resultSet) throws EntityProcessorException;
}
