package com.epam.news.persistence.util.processor.impl;

import com.epam.news.domain.News;
import com.epam.news.persistence.util.processor.EntityProcessor;
import com.epam.news.exception.EntityProcessorException;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * The News processor. Extracts news data from result set and contains comment keys.
 */
@Component
public class NewsProcessor implements EntityProcessor<News> {

    public static final String NEWS_ID_KEY = "NEWS_ID";
    public static final String TITLE_KEY = "TITLE";
    public static final String SHORT_TEST_KEY = "SHORT_TEXT";
    public static final String FULL_TEXT_KEY = "FULL_TEXT";
    public static final String CREATION_DATE_KEY = "CREATION_DATE";
    public static final String MODIFICATION_DATE_KEY = "MODIFICATION_DATE";
    public static final String COUNT = "COUNT";

    /**
     * Extracts news entry object from result set.
     *
     * @param resultSet the result set
     * @return extracted news
     * @throws EntityProcessorException in case of result set is empty
     *                                  or any exception was thrown in method
     */
    @Override
    public News toEntity(ResultSet resultSet) throws EntityProcessorException {
        try {
            if (!resultSet.next()) {
                throw new EntityProcessorException("Result set is empty");
            }

            return getEntity(resultSet);
        } catch (SQLException | EntityProcessorException e) {
            throw new EntityProcessorException("Couldn't get news entry from result set", e);
        }
    }

    /**
     * Extracts the list of news from result set
     *
     * @param resultSet the result set
     * @return the list of extracted news
     * @throws EntityProcessorException if any exception was thrown in method
     */
    @Override
    public List<News> toEntityList(ResultSet resultSet) throws EntityProcessorException {
        try {
            List<News> newsList = new ArrayList<>();

            while (resultSet.next()) {
                newsList.add(getEntity(resultSet));
            }

            return newsList;
        } catch (SQLException e) {
            throw new EntityProcessorException("Couldn't get news list from result set", e);
        }
    }

    private News getEntity(ResultSet resultSet) throws SQLException {
        News news = new News();

        long newsId = resultSet.getLong(NEWS_ID_KEY);
        String title = resultSet.getString(TITLE_KEY);
        String shortText = resultSet.getString(SHORT_TEST_KEY);
        String fullText = resultSet.getString(FULL_TEXT_KEY);
        Timestamp creationDate = resultSet.getTimestamp(CREATION_DATE_KEY);
        Timestamp modificationDate = resultSet.getTimestamp(MODIFICATION_DATE_KEY);

        news.setNewsId(newsId);
        news.setTitle(title);
        news.setShortText(shortText);
        news.setFullText(fullText);
        news.setCreationDate(creationDate);
        news.setModificationDate(modificationDate);

        return news;
    }
}
