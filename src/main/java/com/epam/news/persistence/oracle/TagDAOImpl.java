package com.epam.news.persistence.oracle;

import com.epam.news.domain.Tag;
import com.epam.news.exception.DAOException;
import com.epam.news.exception.EntityProcessorException;
import com.epam.news.persistence.TagDAO;
import com.epam.news.persistence.util.DAOUtil;
import com.epam.news.persistence.util.processor.EntityProcessor;
import com.epam.news.persistence.util.processor.impl.TagProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Oracle Tag data access object. Provides operations with Tag table in database.
 */
@Repository
public class TagDAOImpl implements TagDAO {

    private static final String SQL_ADD_TAG_QUERY = "INSERT INTO TAG (TAG_NAME) VALUES (?)";
    private static final String SQL_FIND_TAG_BY_ID_QUERY = "SELECT TAG_ID, TAG_NAME FROM TAG WHERE TAG_ID = ?";
    private static final String SQL_UPDATE_TAG_QUERY = "UPDATE TAG SET TAG_NAME = ? WHERE TAG_ID = ?";
    private static final String SQL_DELETE_TAG_QUERY = "DELETE FROM TAG WHERE TAG_ID = ?";
    private static final String SQL_GET_ALL_TAGS_QUERY = "SELECT TAG_ID, TAG_NAME FROM TAG";
    private static final String SQL_ADD_NEWS_TAG_QUERY = "INSERT INTO NEWS_TAG (NEWS_ID, TAG_ID) VALUES (?, ?)";
    private static final String SQL_GET_NEWS_TAGS_QUERY = "SELECT TAG.TAG_ID, TAG.TAG_NAME FROM TAG " +
            "INNER JOIN NEWS_TAG ON TAG.TAG_ID = NEWS_TAG.TAG_ID WHERE NEWS_TAG.NEWS_ID = ?";
    private static final String SQL_DELETE_NEWS_TAGS_QUERY = "DELETE FROM NEWS_TAG WHERE NEWS_ID = ?";

    @Autowired
    private DataSource dataSource;
    @Autowired
    private EntityProcessor<Tag> entityProcessor;

    /**
     * Add new tag to database
     *
     * @param tag to add
     * @return added tag with generated id
     * @throws DAOException if SQLException thrown
     */
    @Override
    public Tag add(Tag tag) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            String[] generatedKeyName = {TagProcessor.TAG_ID_KEY};
            statement = connection.prepareStatement(SQL_ADD_TAG_QUERY, generatedKeyName);

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
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Search tag by id
     *
     * @param id tag id
     * @return found tag
     * @throws DAOException if SQLException or EntityProcessorException thrown
     */
    @Override
    public Tag find(Long id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SQL_FIND_TAG_BY_ID_QUERY);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            return entityProcessor.toEntity(resultSet);
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't find tag by id", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Update tag record
     *
     * @param tag to be updated
     * @return true in case of success
     * @throws DAOException if SQLException thrown
     */
    @Override
    public boolean update(Tag tag) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SQL_UPDATE_TAG_QUERY);

            statement.setString(1, tag.getTagName());
            statement.setLong(2, tag.getTagId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Couldn't update tag", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Delete record from database by tag id
     *
     * @param id tag id
     * @return true in case of success
     * @throws DAOException if SQLException thrown
     */
    @Override
    public boolean delete(Long id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SQL_DELETE_TAG_QUERY);

            statement.setLong(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Couldn't delete tag with id", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Get findAll tag
     *
     * @return list of tags
     * @throws DAOException if SQLException or EntityProcessorException thrown
     */
    @Override
    public List<Tag> findAll() throws DAOException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_TAGS_QUERY);

            return entityProcessor.toEntityList(resultSet);
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't get findAll tags", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Add multiple tags.
     *
     * @param tags the tags
     * @return generated id array
     * @throws DAOException the service exception
     */
    @Override
    public long[] addTags(List<Tag> tags) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            String[] generatedKeyName = {TagProcessor.TAG_ID_KEY};
            statement = connection.prepareStatement(SQL_ADD_TAG_QUERY, generatedKeyName);

            for (Tag tag : tags) {
                statement.setString(1, tag.getTagName());
                statement.addBatch();
            }

            statement.executeBatch();
            ResultSet resultSet = statement.getGeneratedKeys();

            long[] idArray = new long[tags.size()];
            for (int i = 0; i < idArray.length && resultSet.next(); i++) {
                idArray[i] = resultSet.getLong(1);
            }

            return idArray;
        } catch (SQLException e) {
            throw new DAOException("Couldn't add new tags", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Add news tag to News_Tag table
     *
     * @param newsId the news id
     * @param tagIdArray the tag id array
     * @throws DAOException if SQLException thrown
     */
    @Override
    public void addNewsTags(long newsId, long... tagIdArray) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SQL_ADD_NEWS_TAG_QUERY);

            for (long tagId : tagIdArray) {
                statement.setLong(1, newsId);
                statement.setLong(2, tagId);
                statement.addBatch();
            }

            statement.executeBatch();
        } catch (SQLException e) {
            throw new DAOException("Couldn't add news author", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Get findAll tags for news entry
     *
     * @param newsId the news id
     * @return list of tags
     * @throws DAOException if SQLException or EntityProcessorException thrown
     */
    @Override
    public List<Tag> getNewsTags(long newsId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SQL_GET_NEWS_TAGS_QUERY);

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

    /**
     * Delete news tags.
     *
     * @param newsId the news id
     * @throws DAOException if SQLException thrown
     */
    @Override
    public void deleteNewsTags(long newsId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SQL_DELETE_NEWS_TAGS_QUERY);

            statement.setLong(1, newsId);
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Couldn't delete tags by news id", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }
}
