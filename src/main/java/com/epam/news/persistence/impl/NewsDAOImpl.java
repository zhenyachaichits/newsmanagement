package com.epam.news.persistence.impl;

import com.epam.news.persistence.NewsDAO;
import com.epam.news.persistence.exception.DAOException;
import com.epam.news.persistence.util.DAOUtil;
import com.epam.news.persistence.util.processor.EntityProcessor;
import com.epam.news.persistence.util.processor.exception.EntityProcessorException;
import com.epam.news.persistence.util.processor.impl.AuthorProcessor;
import com.epam.news.persistence.util.processor.impl.NewsProcessor;
import com.epam.news.domain.News;
import com.epam.news.domain.criteria.NewsSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            DAOUtil.releaseConnection(connection, dataSource);
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
            DAOUtil.releaseConnection(connection, dataSource);
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
            DAOUtil.releaseConnection(connection, dataSource);
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
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public List<News> all() throws DAOException {
        try {
            return getNewsList(SQL_GET_ALL_NEWS_QUERY);
        } catch (DAOException e) {
            throw new DAOException("Couldn't get all news", e);
        }
    }

    @Override
    public List<News> getNewsByTags(Set<Long> tagIdSet) throws DAOException {
        try {
            return getByIdSet(SQL_GET_NEWS_BY_TAGS_QUERY, tagIdSet);
        } catch (DAOException e) {
            throw new DAOException("Couldn't get news by tags", e);
        }
    }

    @Override
    public List<News> getNewsByAuthors(Set<Long> authorSet) throws DAOException {
        try {
            return getByIdSet(SQL_GET_NEWS_BY_AUTHORS_QUERY, authorSet);
        } catch (DAOException e) {
            throw new DAOException("Couldn't get news by authors", e);
        }
    }

    @Override
    public List<News> getNewsByCriteria(NewsSearchCriteria criteria) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(SQL_GET_NEWS_BY_SEARCH_CRITERIA_QUERY);

            statement.setString(1, StringUtils.collectionToCommaDelimitedString(criteria.getAuthorIdSet()));
            statement.setString(2, StringUtils.collectionToCommaDelimitedString(criteria.getTagIdSet()));

            ResultSet resultSet = statement.executeQuery();
            List<News> newsList = entityProcessor.toEntityList(resultSet);

            return newsList;
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't get news by criteria", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public List<News> getNewsOrderedByCommentsNumber() throws DAOException {
        try {
            return getNewsList(SQL_GET_NEWS_ORDERED_BY_COMMENTS_QUERY);
        } catch (DAOException e) {
            throw new DAOException("Couldn't get news ordered by comments number", e);
        }
    }

    @Override
    public int getNewsCount() throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_GET_NEWS_COUNT_QUERY);

            if(resultSet.next()) {
                return resultSet.getInt(NewsProcessor.COUNT);
            } else {
                throw new DAOException("Count was not found. Result set is empty");
            }
        } catch (SQLException e) {
            throw new DAOException("Couldn't get all news count", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    private List<News> getByIdSet(String query, Set<Long> idSet) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, StringUtils.collectionToCommaDelimitedString(idSet));

            ResultSet resultSet = statement.executeQuery();
            List<News> newsList = entityProcessor.toEntityList(resultSet);

            return newsList;
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't get news by id set", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    private List<News> getNewsList(String query) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            List<News> newsList = entityProcessor.toEntityList(resultSet);

            return newsList;
        } catch (SQLException | EntityProcessorException  e) {
            throw new DAOException("Couldn't get news list", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

}
