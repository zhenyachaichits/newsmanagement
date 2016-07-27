package com.epam.news.admin.validation;

import com.epam.news.common.domain.to.NewsTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class NewsFormValidator implements Validator {

    private static final String FIELD_TITLE = "news.title";
    private static final String FIELD_SHORT_TEXT = "news.shortText";
    private static final String FIELD_FULL_TEXT = "news.fullText";
    private static final String FIELD_AUTHORS = "authorIdList";

    public static final String PROPERTY_TITLE_REQUIRED = "title.required";
    public static final String PROPERTY_SHORT_TEXT_REQUIRED = "short.required";
    public static final String PROPERTY_FULL_TEXT_REQUIRED = "full.required";
    public static final String PROPERTY_AUTHORS_REQUIRED = "authors.required";

    @Override
    public boolean supports(Class<?> aClass) {
        return NewsTO.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_TITLE, PROPERTY_TITLE_REQUIRED);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_SHORT_TEXT, PROPERTY_SHORT_TEXT_REQUIRED);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_FULL_TEXT, PROPERTY_FULL_TEXT_REQUIRED);

        NewsTO news = (NewsTO) object;
        List<Long> authorIds = news.getAuthorIdList();
        if(authorIds == null || authorIds.isEmpty()){
            errors.reject(FIELD_AUTHORS, PROPERTY_AUTHORS_REQUIRED);
        }
    }
}
