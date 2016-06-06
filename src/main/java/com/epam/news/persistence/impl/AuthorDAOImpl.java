package com.epam.news.persistence.impl;

import com.epam.news.persistence.AuthorDAO;
import com.epam.news.persistence.exception.DAOException;
import com.epam.news.persistence.util.DAOUtil;
import com.epam.news.persistence.util.processor.EntityProcessor;
import com.epam.news.persistence.util.processor.exception.EntityProcessorException;
import com.epam.news.persistence.util.processor.impl.AuthorProcessor;
import com.epam.news.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Yauhen_Chaichyts on 5/31/2016.
 */
@Repository
public class AuthorDAOImpl implements AuthorDAO {

    private static final String SQL_ADD_AUTHOR_QUERY = "INSERT INTO AUTHOR (AUTHOR_NAME, EXPIRED) VALUES (?, ?)";
    private static final String SQL_FIND_AUTHOR_BY_ID_QUERY = "SELECT AUTHOR_ID, AUTHOR_NAME, EXPIRED FROM AUTHOR " +
            "WHERE AUTHOR_ID = ?";
    private static final String SQL_UPDATE_AUTHOR_QUERY = "UPDATE AUTHOR SET AUTHOR_NAME = ?, EXPIRED = ? " +
            "WHERE AUTHOR_ID = ?";
    private static final String SQL_DELETE_AUTHOR_QUERY = "DELETE FROM AUTHOR WHERE AUTHOR_ID = ?";
    private static final String SQL_GET_ALL_AUTHORS_QUERY = "SELECT AUTHOR_ID, AUTHOR_NAME, EXPIRED FROM AUTHOR";
    private static final String SQL_ADD_NEWS_AUTHOR_QUERY = "INSERT INTO NEWS_AUTHOR (NEWS_ID, AUTHOR_ID) " +
            "VALUES (?, ?)";
    private static final String SQL_GET_NEWS_AUTHORS_QUERY = "SELECT AUTHOR.AUTHOR_ID, AUTHOR.AUTHOR_NAME, " +
            "AUTHOR.EXPIRED FROM AUTHOR INNER JOIN NEWS_AUTHOR ON AUTHOR.AUTHOR_ID = NEWS_AUTHOR.AUTHOR_ID " +
            "WHERE NEWS_AUTHOR.NEWS_ID = ?";

    @Autowired
    private DataSource dataSource;
    @Autowired
    private EntityProcessor<Author> entityProcessor;

    @Override
    public Author add(Author author) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            String[] generatedKeyName = {AuthorProcessor.AUTHOR_ID_KEY};
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_AUTHOR_QUERY, generatedKeyName);

            statement.setString(1, author.getAuthorName());
            statement.setTimestamp(2, author.getExpiredDate());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                author.setAuthorId(resultSet.getLong(1));
            }

            return author;
        } catch (SQLException e) {
            throw new DAOException("Couldn't add new author", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public Author find(Long id) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_AUTHOR_BY_ID_QUERY);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Author author = entityProcessor.toEntity(resultSet);

            return author;
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't find author by id", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public boolean update(Author author) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_AUTHOR_QUERY);

            statement.setString(1, author.getAuthorName());
            statement.setTimestamp(2, author.getExpiredDate());
            statement.setLong(2, author.getAuthorId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Couldn't update author", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_AUTHOR_QUERY);

            statement.setLong(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Couldn't delete author with id", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public List<Author> all() throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_AUTHORS_QUERY);
            List<Author> authorList = entityProcessor.toEntityList(resultSet);

            return authorList;
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't get all authors", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public void addNewsAuthor(long newsId, long authorId) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_NEWS_AUTHOR_QUERY);

            statement.setLong(1, newsId);
            statement.setLong(2, authorId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Couldn't add news author", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public List<Author> getNewsAuthors(long newsId) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(SQL_GET_NEWS_AUTHORS_QUERY);

            statement.setLong(1, newsId);
            ResultSet resultSet = statement.executeQuery();
            List<Author> authorList = entityProcessor.toEntityList(resultSet);

            return authorList;
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't get id set", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }
}
