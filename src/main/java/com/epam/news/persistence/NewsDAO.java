package com.epam.news.persistence;

        import com.epam.news.persistence.exception.DAOException;
        import com.epam.news.domain.News;
        import com.epam.news.domain.criteria.NewsSearchCriteria;

        import java.util.List;
        import java.util.Set;

/**
 * Created by Yauhen_Chaichyts on 5/27/2016.
 */

// TODO: 6/1/2016 set or list?
public interface NewsDAO extends EntityDAO<Long, News> {

    // TODO: 6/3/2016  remove
    Set<Long> getNewsAuthors(long newsId) throws DAOException;

    Set<Long> getNewsTags(long newsId) throws DAOException;

    List<News> getNewsByTags(Set<Long> tagIdSet) throws DAOException;

    List<News> getNewsByAuthors(Set<Long> authorSet) throws DAOException;

    List<News> getNewsByCriteria(NewsSearchCriteria criteria) throws DAOException;

    int getNewsCount() throws DAOException;
}
