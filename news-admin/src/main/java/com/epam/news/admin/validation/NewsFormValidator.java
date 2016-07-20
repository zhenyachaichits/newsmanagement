package com.epam.news.admin.validation;

import com.epam.news.common.domain.to.NewsTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class NewsFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return NewsTO.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "news.title", "title.required");
    }
}
