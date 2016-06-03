package com.epam.news.persistence.util.processor.impl;

import com.epam.news.persistence.util.processor.EntityProcessor;
import com.epam.news.persistence.util.processor.exception.EntityProcessorException;
import com.epam.news.domain.Tag;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yauhen_Chaichyts on 5/31/2016.
 */
@Component
public class TagProcessor implements EntityProcessor<Tag> {

    public static final String TAG_ID_KEY = "TAG_ID";
    public static final String TAG_NAME_KEY = "TAG_NAME_KEY";

    @Override
    public Tag toEntity(ResultSet resultSet) throws EntityProcessorException {
        try{
            Tag tag = new Tag();

            long tagId = resultSet.getLong(TAG_ID_KEY);
            String tagName = resultSet.getString(TAG_NAME_KEY);

            tag.setTagId(tagId);
            tag.setTagName(tagName);

            return tag;
        } catch (SQLException e) {
            throw new EntityProcessorException("Couldn't get tag from result set", e);
        }
    }

    @Override
    public List<Tag> toEntityList(ResultSet resultSet) throws EntityProcessorException {
        try {
            List<Tag> tagList = new ArrayList<>();

            while (resultSet.next()) {
                tagList.add(toEntity(resultSet));
            }

            return tagList;
        } catch (SQLException | EntityProcessorException e) {
            throw new EntityProcessorException("Couldn't get tag list from result set", e);
        }
    }
}
