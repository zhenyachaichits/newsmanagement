package com.epam.news.dao.util.processor.impl;

import com.epam.news.dao.util.processor.EntityProcessor;
import com.epam.news.dao.util.processor.exception.EntityProcessorException;
import com.epam.news.domain.News;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yauhen_Chaichyts on 5/31/2016.
 */
@Component
public class NewsProcessor implements EntityProcessor<News> {

    public static final String NEWS_ID_KEY = "NEWS_ID";
    public static final String TITLE_KEY = "TITLE";
    public static final String SHORT_TEST_KEY = "SHORT_TEXT";
    public static final String FULL_TEXT_KEY = "FULL_TEXT";
    public static final String CREATION_DATE_KEY = "CREATION_DATE";
    public static final String MODIFICATION_DATE_KEY = "MODIFICATION_DATE";

    @Override
    public News toEntity(ResultSet resultSet) throws EntityProcessorException {
       try {
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
       } catch (SQLException e) {
           throw new EntityProcessorException("Couldn't get news entry from result set", e);
       }
    }

    @Override
    public List<News> toEntityList(ResultSet resultSet) throws EntityProcessorException {
        try {
            List<News> newsList = new ArrayList<>();

            while (resultSet.next()) {
                newsList.add(toEntity(resultSet));
            }

            return newsList;
        } catch (SQLException | EntityProcessorException e) {
            throw new EntityProcessorException("Couldn't get news list from result set", e);
        }
    }
}
