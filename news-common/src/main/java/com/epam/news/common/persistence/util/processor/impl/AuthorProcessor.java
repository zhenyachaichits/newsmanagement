package com.epam.news.common.persistence.util.processor.impl;

import com.epam.news.common.domain.Author;
import com.epam.news.common.persistence.util.processor.EntityProcessor;
import com.epam.news.common.exception.EntityProcessorException;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * The Author processor. Extracts author data from result set and contains author keys
 */
@Component
public class AuthorProcessor implements EntityProcessor<Author> {

    public static final String AUTHOR_ID_KEY = "AUTHOR_ID";
    public static final String AUTHOR_NAME_KEY = "AUTHOR_NAME";
    public static final String EXPIRED_STATE_KEY = "EXPIRED";

    /**
     * Extracts author object from result set.
     *
     * @param resultSet the result set
     * @return extracted author
     * @throws EntityProcessorException in case of result set is empty
     *                                  or any exception was thrown in method
     */
    @Override
    public Author toEntity(ResultSet resultSet) throws EntityProcessorException {
        try {
            if (!resultSet.next()) {
                throw new EntityProcessorException("Result set is empty");
            }

            return getEntity(resultSet);
        } catch (SQLException | EntityProcessorException e) {
            throw new EntityProcessorException("Couldn't get author from result set", e);
        }
    }

    /**
     * Extracts the list of authors from result set
     *
     * @param resultSet the result set
     * @return the list of extracted authors
     * @throws EntityProcessorException if any exception was thrown in method
     */
    @Override
    public List<Author> toEntityList(ResultSet resultSet) throws EntityProcessorException {
        try {
            List<Author> authorList = new ArrayList<>();

            while (resultSet.next()) {
                authorList.add(getEntity(resultSet));
            }

            return authorList;
        } catch (SQLException e) {
            throw new EntityProcessorException("Couldn't get author list from result set", e);
        }
    }

    private Author getEntity(ResultSet resultSet) throws SQLException {
        Author author = new Author();

        long authorId = resultSet.getLong(AUTHOR_ID_KEY);
        String authorName = resultSet.getString(AUTHOR_NAME_KEY);
        Timestamp expiredDate = resultSet.getTimestamp(EXPIRED_STATE_KEY);

        author.setAuthorId(authorId);
        author.setAuthorName(authorName);
        author.setExpiredDate(expiredDate);

        return author;
    }
}
