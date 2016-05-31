package com.epam.news.dao.impl;

import com.epam.news.dao.AuthorDAO;
import com.epam.news.dao.exception.DAOException;
import com.epam.news.dao.util.ConnectionProvider;
import com.epam.news.dao.util.processor.EntityProcessor;
import com.epam.news.dao.util.processor.exception.EntityProcessorException;
import com.epam.news.dao.util.processor.impl.AuthorProcessor;
import com.epam.news.domain.Author;
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
public class AuthorDAOImpl implements AuthorDAO {

    private static final String SQL_ADD_AUTHOR_QUERY = "INSERT INTO AUTHOR (AUTHOR_NAME, EXPIRED) VALUES (?, ?)";
    private static final String SQL_FIND_AUTHOR_BY_ID_QUERY = "SELECT AUTHOR_ID, AUTHOR_NAME, EXPIRED FROM AUTHOR " +
            "WHERE AUTHOR_ID = ?";
    private static final String SQL_UPDATE_AUTHOR_QUERY = "UPDATE AUTHOR SET AUTHOR_NAME = ?, EXPIRED = ? " +
            "WHERE AUTHOR_ID = ?";
    private static final String SQL_DELETE_AUTHOR_QUERY = "DELETE FROM AUTHOR WHERE AUTHOR_ID = ?";
    private static final String SQL_GET_ALL_AUTHORS_QUERY = "SELECT AUTHOR_ID, AUTHOR_NAME, EXPIRED FROM AUTHOR";

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
            ConnectionProvider.releaseConnection(connection, dataSource);
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
            ConnectionProvider.releaseConnection(connection, dataSource);
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
            ConnectionProvider.releaseConnection(connection, dataSource);
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
            ConnectionProvider.releaseConnection(connection, dataSource);
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
            ConnectionProvider.releaseConnection(connection, dataSource);
        }
    }
}
