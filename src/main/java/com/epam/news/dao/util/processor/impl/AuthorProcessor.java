package com.epam.news.dao.util.processor.impl;

import com.epam.news.dao.util.processor.EntityProcessor;
import com.epam.news.dao.util.processor.exception.EntityProcessorException;
import com.epam.news.domain.Author;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yauhen_Chaichyts on 5/31/2016.
 */
@Component
public class AuthorProcessor implements EntityProcessor<Author> {

    public static final String AUTHOR_ID_KEY = "AUTHOR_ID";
    public static final String AUTHOR_NAME_KEY = "AUTHOR_NAME";
    public static final String EXPIRED_STATE_KEY = "EXPIRED";

    @Override
    public Author toEntity(ResultSet resultSet) throws EntityProcessorException {
        try {
            Author author = new Author();

            long authorId = resultSet.getLong(AUTHOR_ID_KEY);
            String authorName = resultSet.getString(AUTHOR_NAME_KEY);
            Timestamp expiredDate = resultSet.getTimestamp(EXPIRED_STATE_KEY);

            author.setAuthorId(authorId);
            author.setAuthorName(authorName);
            author.setExpiredDate(expiredDate);

            return author;
        } catch (SQLException e) {
            throw new EntityProcessorException("Couldn't get author from result set", e);
        }
    }

    @Override
    public List<Author> toEntityList(ResultSet resultSet) throws EntityProcessorException {
        try {
            List<Author> authorList = new ArrayList<>();

            while (resultSet.next()) {
                authorList.add(toEntity(resultSet));
            }

            return authorList;
        } catch (SQLException | EntityProcessorException e) {
            throw new EntityProcessorException("Couldn't get author list from result set", e);
        }
    }
}
