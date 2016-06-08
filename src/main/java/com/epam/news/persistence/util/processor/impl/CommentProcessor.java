package com.epam.news.persistence.util.processor.impl;

import com.epam.news.persistence.util.processor.EntityProcessor;
import com.epam.news.persistence.util.processor.exception.EntityProcessorException;
import com.epam.news.domain.Comment;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yauhen_Chaichyts on 5/31/2016.
 */
@Component
public class CommentProcessor implements EntityProcessor<Comment> {

    public static final String COMMENT_ID_KEY = "COMMENT_ID";
    public static final String NEWS_ID_KEY = "NEWS_ID";
    public static final String COMMENT_TEXT_KEY = "COMMENT_TEXT";
    public static final String CREATION_DATE_KEY = "CREATION_DATE";

    @Override
    public Comment toEntity(ResultSet resultSet) throws EntityProcessorException {
        try {
            if (!resultSet.next()) {
                throw new EntityProcessorException("Result set is empty");
            }

            return getEntity(resultSet);
        } catch (SQLException | EntityProcessorException e) {
            throw new EntityProcessorException("Couldn't get comment from result set", e);
        }
    }

    @Override
    public List<Comment> toEntityList(ResultSet resultSet) throws EntityProcessorException {
        try {
            List<Comment> commentList = new ArrayList<>();

            while (resultSet.next()) {
                commentList.add(getEntity(resultSet));
            }

            return commentList;
        } catch (SQLException e) {
            throw new EntityProcessorException("Couldn't get comment list from result set", e);
        }
    }

    private Comment getEntity(ResultSet resultSet) throws SQLException {
        Comment comment = new Comment();

        long commentId = resultSet.getLong(COMMENT_ID_KEY);
        long newsId = resultSet.getLong(NEWS_ID_KEY);
        String commentText = resultSet.getString(COMMENT_TEXT_KEY);
        Timestamp creationDate = resultSet.getTimestamp(CREATION_DATE_KEY);

        comment.setCommentId(commentId);
        comment.setNewsId(newsId);
        comment.setCommentText(commentText);
        comment.setCreationDate(creationDate);

        return comment;
    }
}
