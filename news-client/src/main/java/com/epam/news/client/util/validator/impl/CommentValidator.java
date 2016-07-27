package com.epam.news.client.util.validator.impl;

import com.epam.news.client.util.validator.BeanValidator;
import com.epam.news.common.domain.Comment;
import org.springframework.stereotype.Component;

/**
 * Created by Yauhen_Chaichyts on 7/25/2016.
 */
@Component
public class CommentValidator implements BeanValidator<Comment> {
    @Override
    public boolean validate(Comment comment) {
        if (comment.getCommentText().isEmpty() || comment.getNewsId() > 0) {
            return false;
        }

        return true;
    }
}
