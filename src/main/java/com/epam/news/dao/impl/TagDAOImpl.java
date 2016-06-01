package com.epam.news.dao.impl;

import com.epam.news.dao.TagDAO;
import com.epam.news.dao.exception.DAOException;
import com.epam.news.dao.util.DAOUtil;
import com.epam.news.dao.util.processor.EntityProcessor;
import com.epam.news.dao.util.processor.exception.EntityProcessorException;
import com.epam.news.dao.util.processor.impl.TagProcessor;
import com.epam.news.domain.Tag;
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
public class TagDAOImpl implements TagDAO {

    private static final String SQL_ADD_TAG_QUERY = "INSERT INTO TAG (TAG_NAME) VALUES (?)";
    private static final String SQL_FIND_TAG_BY_ID_QUERY = "SELECT TAG_ID, TAG_NAME FROM TAG WHERE TAG_ID = ?";
    private static final String SQL_UPDATE_TAG_QUERY = "UPDATE TAG SET TAG_NAME = ? WHERE TAG_ID = ?";
    private static final String SQL_DELETE_TAG_QUERY = "DELETE FROM TAG WHERE TAG_ID = ?";
    private static final String SQL_GET_ALL_TAGS_QUERY = "SELECT TAG_ID, TAG_NAME FROM TAG";

    @Autowired
    private DataSource dataSource;
    @Autowired
    private EntityProcessor<Tag> entityProcessor;

    @Override
    public Tag add(Tag tag) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            String[] generatedKeyName = {TagProcessor.TAG_ID_KEY};
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_TAG_QUERY, generatedKeyName);

            statement.setString(1, tag.getTagName());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                tag.setTagId(resultSet.getLong(1));
            }

            return tag;
        } catch (SQLException e) {
            throw new DAOException("Couldn't add new tag", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public Tag find(Long id) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_TAG_BY_ID_QUERY);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Tag tag = entityProcessor.toEntity(resultSet);

            return tag;
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't find tag by id", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public boolean update(Tag tag) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_TAG_QUERY);

            statement.setString(1, tag.getTagName());
            statement.setLong(2, tag.getTagId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Couldn't update tag", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_TAG_QUERY);

            statement.setLong(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Couldn't delete tag with id", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public List<Tag> all() throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_TAGS_QUERY);
            List<Tag> tagList = entityProcessor.toEntityList(resultSet);

            return tagList;
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't get all tags", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }
}
