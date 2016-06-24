package com.epam.news.common.persistence.oracle;

import com.epam.news.common.domain.News;
import com.epam.news.common.domain.criteria.NewsSearchCriteria;
import com.epam.news.common.persistence.NewsDAO;
import com.epam.news.common.exception.DAOException;
import com.epam.news.common.persistence.util.DAOUtil;
import com.epam.news.common.persistence.util.processor.EntityProcessor;
import com.epam.news.common.exception.EntityProcessorException;
import com.epam.news.common.persistence.util.processor.impl.NewsProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Set;

/**
 * Oracle News data access object. Provides operations with news table in database.
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
    private static final String SQL_GET_NEWS_BY_AUTHORS_QUERY = "SELECT NEWS.NEWS_ID, NEWS.TITLE, " +
            "NEWS.SHORT_TEXT, NEWS.FULL_TEXT, NEWS.CREATION_DATE, NEWS.MODIFICATION_DATE FROM NEWS " +
            "INNER JOIN NEWS_AUTHOR ON NEWS.NEWS_ID = NEWS_AUTHOR.NEWS_ID WHERE NEWS_AUTHOR.AUTHOR_ID IN (?)";
    private static final String SQL_GET_NEWS_BY_TAGS_QUERY = "SELECT NEWS.NEWS_ID, NEWS.TITLE, " +
            "NEWS.SHORT_TEXT, NEWS.FULL_TEXT, NEWS.CREATION_DATE, NEWS.MODIFICATION_DATE FROM NEWS " +
            "INNER JOIN NEWS_TAG ON NEWS.NEWS_ID = NEWS_TAG.NEWS_ID WHERE NEWS_TAG.TAG_ID IN (?)";
    private static final String SQL_GET_NEWS_ORDERED_BY_COMMENTS_QUERY = "SELECT NEWS.NEWS_ID, NEWS.TITLE, " +
            "NEWS.SHORT_TEXT, NEWS.FULL_TEXT, NEWS.CREATION_DATE, NEWS.MODIFICATION_DATE, " +
            "COUNT(COMMENTS.COMMENT_ID) AS COUNT FROM NEWS LEFT JOIN COMMENTS " +
            "ON NEWS.NEWS_ID = COMMENTS.NEWS_ID GROUP BY NEWS.NEWS_ID, NEWS.TITLE, NEWS.SHORT_TEXT, " +
            "NEWS.FULL_TEXT, NEWS.CREATION_DATE, NEWS.MODIFICATION_DATE ORDER BY COUNT DESC";
    private static final String SQL_GET_NEWS_BY_SEARCH_CRITERIA_QUERY = "SELECT NEWS.NEWS_ID, NEWS.TITLE, " +
            "NEWS.SHORT_TEXT, NEWS.FULL_TEXT, NEWS.CREATION_DATE, NEWS.MODIFICATION_DATE FROM NEWS " +
            "INNER JOIN NEWS_TAG ON NEWS.NEWS_ID = NEWS_TAG.NEWS_ID INNER JOIN NEWS_AUTHOR " +
            "ON NEWS.NEWS_ID = NEWS_AUTHOR.NEWS_ID WHERE NEWS_AUTHOR.AUTHOR_ID IN (?) AND NEWS_TAG.TAG_ID IN (?)";
    private static final String SQL_GET_NEWS_COUNT_QUERY = "SELECT COUNT(*) AS COUNT FROM NEWS";

    @Autowired
    private DataSource dataSource;
    @Autowired
    private EntityProcessor<News> entityProcessor;

    /**
     * Add new news record to database
     *
     * @param news to add
     * @return added news with generated id
     * @throws DAOException if SQLException thrown
     */
    @Override
    public News add(News news) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            String[] generatedKeyName = {NewsProcessor.NEWS_ID_KEY};
            statement = connection.prepareStatement(SQL_ADD_NEWS_QUERY, generatedKeyName);

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
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Search news by id
     *
     * @param id id of news entry
     * @return found news
     * @throws DAOException if SQLException or EntityProcessorException thrown
     */
    @Override
    public News find(Long id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SQL_FIND_NEWS_BY_ID_QUERY);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            return entityProcessor.toEntity(resultSet);
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't find news by id", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Update news record
     *
     * @param news to be updated
     * @return true in case of success
     * @throws DAOException if SQLException thrown
     */
    @Override
    public boolean update(News news) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SQL_UPDATE_NEWS_QUERY);

            statement.setString(1, news.getTitle());
            statement.setString(2, news.getShortText());
            statement.setString(3, news.getFullText());
            statement.setTimestamp(4, news.getModificationDate());
            statement.setLong(5, news.getNewsId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Couldn't update news text", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Delete record from database by comment id
     *
     * @param id news id
     * @return true in case of success
     * @throws DAOException if SQLException thrown
     */
    @Override
    public boolean delete(Long id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SQL_DELETE_ROLE_QUERY);

            statement.setLong(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Couldn't delete news with id", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Get findAllNewsData news
     *
     * @return list of news
     * @throws DAOException if SQLException or EntityProcessorException thrown
     */
    @Override
    public List<News> findAll() throws DAOException {
        try {
            return getNewsList(SQL_GET_ALL_NEWS_QUERY);
        } catch (DAOException e) {
            throw new DAOException("Couldn't get findAllNewsData news", e);
        }
    }

    /**
     * Get news by tags
     *
     * @param tagIdSet the tag id set
     * @return list of news
     * @throws DAOException if DAOException thrown
     */
    @Override
    public List<News> getNewsByTags(Set<Long> tagIdSet) throws DAOException {
        try {
            return getByIdSet(SQL_GET_NEWS_BY_TAGS_QUERY, tagIdSet);
        } catch (DAOException e) {
            throw new DAOException("Couldn't get news by tags", e);
        }
    }

    /**
     * Get news by authors
     *
     * @param authorSet the author set
     * @return list of news
     * @throws DAOException if DAOException thrown
     */
    @Override
    public List<News> getNewsByAuthors(Set<Long> authorSet) throws DAOException {
        try {
            return getByIdSet(SQL_GET_NEWS_BY_AUTHORS_QUERY, authorSet);
        } catch (DAOException e) {
            throw new DAOException("Couldn't get news by authors", e);
        }
    }

    /**
     * Get news by criteria, which contains authors and tags
     *
     * @param criteria the criteria
     * @return list of news
     * @throws DAOException if SQLException or EntityProcessorException thrown
     */
    @Override
    public List<News> getNewsByCriteria(NewsSearchCriteria criteria) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SQL_GET_NEWS_BY_SEARCH_CRITERIA_QUERY);

            statement.setString(1, StringUtils.collectionToCommaDelimitedString(criteria.getAuthorIdSet()));
            statement.setString(2, StringUtils.collectionToCommaDelimitedString(criteria.getTagIdSet()));

            ResultSet resultSet = statement.executeQuery();

            return entityProcessor.toEntityList(resultSet);
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't get news by criteria", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Get news ordered by comments number
     *
     * @return list of news
     * @throws DAOException if DAOException thrown
     */
    @Override
    public List<News> getNewsOrderedByCommentsNumber() throws DAOException {
        try {
            return getNewsList(SQL_GET_NEWS_ORDERED_BY_COMMENTS_QUERY);
        } catch (DAOException e) {
            throw new DAOException("Couldn't get news ordered by comments number", e);
        }
    }

    /**
     * Get findAllNewsData news count
     *
     * @return news count
     * @throws DAOException if SQLException thrown
     */
    @Override
    public int getNewsCount() throws DAOException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_GET_NEWS_COUNT_QUERY);

            if (resultSet.next()) {
                return resultSet.getInt(NewsProcessor.COUNT);
            } else {
                throw new DAOException("Count was not found. Result set is empty");
            }
        } catch (SQLException e) {
            throw new DAOException("Couldn't get findAllNewsData news count", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    private List<News> getByIdSet(String query, Set<Long> idSet) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(query);

            statement.setString(1, StringUtils.collectionToCommaDelimitedString(idSet));

            ResultSet resultSet = statement.executeQuery();

            return entityProcessor.toEntityList(resultSet);
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't get news by id set", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    private List<News> getNewsList(String query) throws DAOException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            return entityProcessor.toEntityList(resultSet);
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't get news list", e);
        } finally {
            DAOUtil.closeStatement(statement);
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

}
