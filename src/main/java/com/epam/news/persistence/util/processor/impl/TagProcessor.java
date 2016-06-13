package com.epam.news.persistence.util.processor.impl;

import com.epam.news.domain.Tag;
import com.epam.news.persistence.util.processor.EntityProcessor;
import com.epam.news.exception.EntityProcessorException;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Tag processor. Extracts tag data from result set and contains comment keys.
 */
@Component
public class TagProcessor implements EntityProcessor<Tag> {

    public static final String TAG_ID_KEY = "TAG_ID";
    public static final String TAG_NAME_KEY = "TAG_NAME";

    /**
     * Extracts tag object from result set.
     *
     * @param resultSet the result set
     * @return extracted tag
     * @throws EntityProcessorException in case of result set is empty
     *                                  or any exception was thrown in method
     */
    @Override
    public Tag toEntity(ResultSet resultSet) throws EntityProcessorException {
        try {
            if (!resultSet.next()) {
                throw new EntityProcessorException("Result set is empty");
            }

            return getEntity(resultSet);
        } catch (SQLException | EntityProcessorException e) {
            throw new EntityProcessorException("Couldn't get tag from result set", e);
        }
    }

    /**
     * Extracts the list of tag from result set
     *
     * @param resultSet the result set
     * @return the list of extracted tag
     * @throws EntityProcessorException if any exception was thrown in method
     */
    @Override
    public List<Tag> toEntityList(ResultSet resultSet) throws EntityProcessorException {
        try {
            List<Tag> tagList = new ArrayList<>();

            while (resultSet.next()) {
                tagList.add(getEntity(resultSet));
            }

            return tagList;
        } catch (SQLException e) {
            throw new EntityProcessorException("Couldn't get tag list from result set", e);
        }
    }

    private Tag getEntity(ResultSet resultSet) throws SQLException {

        Tag tag = new Tag();

        long tagId = resultSet.getLong(TAG_ID_KEY);
        String tagName = resultSet.getString(TAG_NAME_KEY);

        tag.setTagId(tagId);
        tag.setTagName(tagName);

        return tag;
    }
}
