package com.epam.news.persistence.oracle;

import com.epam.news.domain.Comment;
import com.epam.news.persistence.CommentDAO;
import com.epam.news.exception.DAOException;
import com.epam.news.persistence.util.DAOUtil;
import com.epam.news.persistence.util.processor.EntityProcessor;
import com.epam.news.exception.EntityProcessorException;
import com.epam.news.persistence.util.processor.impl.CommentProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;


/**
 * Oracle Comment data access object. Provides operations with Comments table in database.
 */
@Repository
public class CommentDAOImpl implements CommentDAO {

    private static final String SQL_ADD_COMMENT_QUERY = "INSERT INTO COMMENTS (NEWS_ID, COMMENT_TEXT, CREATION_DATE) " +
            "VALUES (?, ?, ?)";
    private static final String SQL_FIND_COMMENT_BY_ID_QUERY = "SELECT COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE " +
            "FROM COMMENTS WHERE COMMENT_ID = ?";
    private static final String SQL_UPDATE_COMMENT_QUERY = "UPDATE COMMENTS SET COMMENT_TEXT = ? WHERE COMMENT_ID = ?";
    private static final String SQL_DELETE_COMMENT_QUERY = "DELETE FROM COMMENTS WHERE COMMENT_ID = ?";
    private static final String SQL_GET_ALL_COMMENTS_QUERY = "SELECT COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE " +
            "FROM COMMENTS";
    private static final String SQL_GET_NEWS_COMMENTS_QUERY = "SELECT COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE " +
            "FROM COMMENTS WHERE NEWS_ID = ?";

    @Autowired
    private DataSource dataSource;
    @Autowired
    private EntityProcessor<Comment> entityProcessor;

    /**
     * Add single comment record to database
     *
     * @param comment to add
     * @return added author with generated id
     * @throws DAOException if SQLException thrown
     */
    @Override
    public Comment add(Comment comment) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            String[] generatedKeyName = {CommentProcessor.COMMENT_ID_KEY};
            statement = connection.prepareStatement(SQL_ADD_COMMENT_QUERY, generatedKeyName);

            statement.setLong(1, comment.getNewsId());
            statement.setString(2, comment.getCommentText());
            statement.setTimestamp(3, comment.getCreationDate());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                comment.setCommentId(resultSet.getLong(1));
            }

            return comment;
        } catch (SQLException e) {
            throw new DAOException("Couldn't add new comment", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }


    /**
     * Add multiple comments data.
     *
     * @param comments the comments
     * @throws DAOException the dao exception
     */
    @Override
    public void addComments(Comment... comments) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SQL_ADD_COMMENT_QUERY);

            for (Comment comment : comments) {
                statement.setLong(1, comment.getNewsId());
                statement.setString(2, comment.getCommentText());
                statement.setTimestamp(3, comment.getCreationDate());
                statement.addBatch();
            }

            statement.executeBatch();
        } catch (SQLException e) {
            throw new DAOException("Couldn't add new comment", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Search comment by id
     *
     * @param commentId id of comment
     * @return found author
     * @throws DAOException if SQLException or EntityProcessorException thrown
     */
    @Override
    public Comment find(Long commentId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SQL_FIND_COMMENT_BY_ID_QUERY);

            statement.setLong(1, commentId);
            ResultSet resultSet = statement.executeQuery();

            return entityProcessor.toEntity(resultSet);
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't find comment by id", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Update comment record
     *
     * @param comment to be updated
     * @return true in case of success
     * @throws DAOException if SQLException thrown
     */
    @Override
    public boolean update(Comment comment) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SQL_UPDATE_COMMENT_QUERY);

            statement.setString(1, comment.getCommentText());
            statement.setLong(2, comment.getCommentId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Couldn't update comment text", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Delete record from database by comment id
     *
     * @param commentId author id
     * @return true in case of success
     * @throws DAOException if SQLException thrown
     */
    @Override
    public boolean delete(Long commentId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SQL_DELETE_COMMENT_QUERY);

            statement.setLong(1, commentId);
            return statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Couldn't delete comment with id", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Delete multiple comments by id.
     *
     * @param commentIdArray the comment id array
     * @throws DAOException in case of error
     */
    @Override
    public void deleteComments(long... commentIdArray) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SQL_DELETE_COMMENT_QUERY);

            for (long commentId : commentIdArray) {
                statement.setLong(1, commentId);
                statement.addBatch();
            }

            statement.executeBatch();
        } catch (SQLException e) {
            throw new DAOException("Couldn't delete comment with id", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }


    /**
     * Get findAll comments
     *
     * @return list of comments
     * @throws DAOException if SQLException or EntityProcessorException thrown
     */
    @Override
    public List<Comment> findAll() throws DAOException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_COMMENTS_QUERY);

            return entityProcessor.toEntityList(resultSet);
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't get findAll comments", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Get findAll news' comments
     *
     * @param newsId news id
     * @return list of comments
     * @throws DAOException
     */
    @Override
    public List<Comment> getNewsComments(long newsId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SQL_GET_NEWS_COMMENTS_QUERY);

            statement.setLong(1, newsId);
            ResultSet resultSet = statement.executeQuery();

            return entityProcessor.toEntityList(resultSet);
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't get findAll news comments", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }
}
