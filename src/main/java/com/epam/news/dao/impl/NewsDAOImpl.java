package com.epam.news.dao.impl;

import com.epam.news.dao.NewsDAO;
import com.epam.news.dao.exception.DAOException;
import com.epam.news.dao.util.ConnectionProvider;
import com.epam.news.dao.util.processor.EntityProcessor;
import com.epam.news.dao.util.processor.exception.EntityProcessorException;
import com.epam.news.dao.util.processor.impl.AuthorProcessor;
import com.epam.news.domain.Author;
import com.epam.news.domain.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created by Yauhen_Chaichyts on 5/31/2016.
 */
@Repository
public class NewsDAOImpl implements NewsDAO {

    private static final String SQL_ADD_NEWS_QUERY = "INSERT INTO NEWS (TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, " +
            "MODIFICATION_DATE) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_FIND_NEWS_BY_ID_QUERY = "SELECT NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, " +
            "CREATION_DATE, MODIFICATION_DATE FROM NEWS WHERE NEWS_ID = ?";
    private static final String SQL_UPDATE_NEWS_QUERY = "UPDATE NEWS SET TITLE = ?, SHORT_TEXT = ?, FULL_TEXT = ?, " +
            "MODIFICATION_DATE = ? WHERE NEWS_ID = ?";
    private static final String SQL_DELETE_ROLE_QUERY = "DELETE FROM NEWS WHERE NEWS_ID = ?";
    private static final String SQL_GET_ALL_NEWS_QUERY = "SELECT NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, " +
            "CREATION_DATE, MODIFICATION_DATE FROM NEWS";



    @Autowired
    private DataSource dataSource;
    @Autowired
    private EntityProcessor<News> entityProcessor;

    @Override
    public News add(News news) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            String[] generatedKeyName = {AuthorProcessor.AUTHOR_ID_KEY};
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_NEWS_QUERY, generatedKeyName);

            statement.setString(1, news.getTitle());
            statement.setString(2, news.getShortText());
            statement.setString(3, news.getFullText());
            statement.setTimestamp(4, news.getCreationDate());
            statement.setTimestamp(5, news.getModificationDate());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                news.setNewsId(resultSet.getLong(1));
            }

            return news;
        } catch (SQLException e) {
            throw new DAOException("Couldn't add new news", e);
        } finally {
            ConnectionProvider.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public News find(Long id) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_NEWS_BY_ID_QUERY);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            News news = entityProcessor.toEntity(resultSet);

            return news;
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't find news by id", e);
        } finally {
            ConnectionProvider.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public boolean update(News news) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_NEWS_QUERY);

            statement.setString(1, news.getTitle());
            statement.setString(2, news.getShortText());
            statement.setString(3, news.getFullText());
            statement.setTimestamp(4, news.getModificationDate());
            statement.setLong(2, news.getNewsId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Couldn't update news text", e);
        } finally {
            ConnectionProvider.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ROLE_QUERY);

            statement.setLong(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Couldn't delete news with id", e);
        } finally {
            ConnectionProvider.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public List<News> all() throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_NEWS_QUERY);
            List<News> newsList = entityProcessor.toEntityList(resultSet);

            return newsList;
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't get all news", e);
        } finally {
            ConnectionProvider.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public List<Author> getNewsAuthors(long newsId) throws DAOException {
        return null;
    }
}
