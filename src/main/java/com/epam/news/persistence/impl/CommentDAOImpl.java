package com.epam.news.persistence.impl;

import com.epam.news.persistence.CommentDAO;
import com.epam.news.persistence.exception.DAOException;
import com.epam.news.persistence.util.DAOUtil;
import com.epam.news.persistence.util.processor.EntityProcessor;
import com.epam.news.persistence.util.processor.exception.EntityProcessorException;
import com.epam.news.persistence.util.processor.impl.CommentProcessor;
import com.epam.news.domain.Comment;
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
public class CommentDAOImpl implements CommentDAO {

    private static final String SQL_ADD_COMMENT_QUERY = "INSERT INTO COMMENTS (NEWS_ID, COMMENT_TEXT, CREATION_DATE) " +
            "VALUES (?, ?, ?)";
    private static final String SQL_FIND_COMMENT_BY_ID_QUERY = "SELECT COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE " +
            "FROM COMMENTS WHERE COMMENT_ID = ?";
    private static final String SQL_UPDATE_COMMENT_QUERY = "UPDATE COMMENTS SET COMMENT_TEXT = ? WHERE COMMENT_ID = ?";
    private static final String SQL_DELETE_COMMENT_QUERY = "DELETE FROM COMMENTS WHERE COMMENT_ID = ?";
    private static final String SQL_GET_ALL_COMMENTS_QUERY = "SELECT COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE " +
            "FROM COMMENTS";

    @Autowired
    private DataSource dataSource;
    @Autowired
    private EntityProcessor<Comment> entityProcessor;

    @Override
    public Comment add(Comment comment) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            String[] generatedKeyName = {CommentProcessor.COMMENT_ID_KEY};
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_COMMENT_QUERY, generatedKeyName);

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
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public Comment find(Long commentId) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_COMMENT_BY_ID_QUERY);

            statement.setLong(1, commentId);
            ResultSet resultSet = statement.executeQuery();
            Comment comment = entityProcessor.toEntity(resultSet);

            return comment;
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't find comment by id", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public boolean update(Comment comment) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_COMMENT_QUERY);

            statement.setString(1, comment.getCommentText());
            statement.setLong(2, comment.getCommentId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Couldn't update comment text", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public boolean delete(Long commentId) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_COMMENT_QUERY);

            statement.setLong(1, commentId);
            return statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Couldn't delete comment with id", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public List<Comment> all() throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_COMMENTS_QUERY);
            List<Comment> tagList = entityProcessor.toEntityList(resultSet);

            return tagList;
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't get all comments", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }
}
