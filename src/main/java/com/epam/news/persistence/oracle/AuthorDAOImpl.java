package com.epam.news.persistence.oracle;

import com.epam.news.domain.Author;
import com.epam.news.persistence.AuthorDAO;
import com.epam.news.exception.DAOException;
import com.epam.news.persistence.util.DAOUtil;
import com.epam.news.persistence.util.processor.EntityProcessor;
import com.epam.news.exception.EntityProcessorException;
import com.epam.news.persistence.util.processor.impl.AuthorProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;


/**
 * Oracle Author data access object. Provides operations with Author table in database.
 */
@Repository
public class AuthorDAOImpl implements AuthorDAO {

    private static final String SQL_ADD_AUTHOR_QUERY = "INSERT INTO AUTHOR (AUTHOR_NAME, EXPIRED) VALUES (?, ?)";
    private static final String SQL_FIND_AUTHOR_BY_ID_QUERY = "SELECT AUTHOR_ID, AUTHOR_NAME, EXPIRED FROM AUTHOR " +
            "WHERE AUTHOR_ID = ? AND EXPIRED >= CURRENT_TIMESTAMP";
    private static final String SQL_UPDATE_AUTHOR_QUERY = "UPDATE AUTHOR SET AUTHOR_NAME = ?, EXPIRED = ? " +
            "WHERE AUTHOR_ID = ?";
    private static final String SQL_DELETE_AUTHOR_QUERY = "DELETE FROM AUTHOR WHERE AUTHOR_ID = ?";
    private static final String SQL_GET_ALL_AUTHORS_QUERY = "SELECT AUTHOR_ID, AUTHOR_NAME, EXPIRED FROM AUTHOR " +
            "WHERE EXPIRED >= CURRENT_TIMESTAMP";
    private static final String SQL_ADD_NEWS_AUTHOR_QUERY = "INSERT INTO NEWS_AUTHOR (NEWS_ID, AUTHOR_ID) " +
            "VALUES (?, ?)";
    private static final String SQL_GET_NEWS_AUTHORS_QUERY = "SELECT AUTHOR.AUTHOR_ID, AUTHOR.AUTHOR_NAME, " +
            "AUTHOR.EXPIRED FROM AUTHOR INNER JOIN NEWS_AUTHOR ON AUTHOR.AUTHOR_ID = NEWS_AUTHOR.AUTHOR_ID " +
            "WHERE NEWS_AUTHOR.NEWS_ID = ?";

    @Autowired
    private DataSource dataSource;
    @Autowired
    private EntityProcessor<Author> entityProcessor;

    /**
     * Add new author to database
     *
     * @param author to add
     * @return added author with generated id
     * @throws DAOException if SQLException thrown
     */
    @Override
    public Author add(Author author) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            String[] generatedKeyName = {AuthorProcessor.AUTHOR_ID_KEY};
            statement = connection.prepareStatement(SQL_ADD_AUTHOR_QUERY, generatedKeyName);

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
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Search author by id
     *
     * @param id of author
     * @return found author
     * @throws DAOException if SQLException or EntityProcessorException thrown
     */
    @Override
    public Author find(Long id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SQL_FIND_AUTHOR_BY_ID_QUERY);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            return entityProcessor.toEntity(resultSet);
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't find author by id", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Update author record
     *
     * @param author to be updated
     * @return true in case of success
     * @throws DAOException if SQLException thrown
     */
    @Override
    public boolean update(Author author) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SQL_UPDATE_AUTHOR_QUERY);

            statement.setString(1, author.getAuthorName());
            statement.setTimestamp(2, author.getExpiredDate());
            statement.setLong(3, author.getAuthorId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Couldn't update author", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Delete record from database by author id
     *
     * @param id author id
     * @return true in case of success
     * @throws DAOException if SQLException thrown
     */
    @Override
    public boolean delete(Long id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SQL_DELETE_AUTHOR_QUERY);

            statement.setLong(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Couldn't delete author with id", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Get findAll authors
     *
     * @return list of authors
     * @throws DAOException if SQLException or EntityProcessorException thrown
     */
    @Override
    public List<Author> findAll() throws DAOException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_AUTHORS_QUERY);

            return entityProcessor.toEntityList(resultSet);
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't get findAll authors", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Add news author to News_Author table
     *
     * @param newsId   the news id
     * @param authorId the author id
     * @throws DAOException if SQLException thrown
     */
    @Override
    public void addNewsAuthor(long newsId, long authorId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SQL_ADD_NEWS_AUTHOR_QUERY);

            statement.setLong(1, newsId);
            statement.setLong(2, authorId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Couldn't add news author", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Get findAll authors for news entry
     *
     * @param newsId the news id
     * @return list of authors
     * @throws DAOException if SQLException or EntityProcessorException thrown
     */
    @Override
    public List<Author> getNewsAuthors(long newsId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SQL_GET_NEWS_AUTHORS_QUERY);

            statement.setLong(1, newsId);
            ResultSet resultSet = statement.executeQuery();

            return entityProcessor.toEntityList(resultSet);
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't get id set", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }
}
