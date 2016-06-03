package com.epam.news.persistence.util.processor;

import com.epam.news.persistence.util.processor.exception.EntityProcessorException;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Zheny Chaichits on 28.05.2016.
 */
public interface EntityProcessor<T> {

    T toEntity(ResultSet resultSet) throws EntityProcessorException;

    List<T> toEntityList(ResultSet resultSet) throws EntityProcessorException;
}
