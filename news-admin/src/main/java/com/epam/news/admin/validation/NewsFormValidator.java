package com.epam.news.admin.validation;

import com.epam.news.common.domain.to.NewsTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Yauhen_Chaichyts on 7/18/2016.
 */
public class NewsFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return NewsTO.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", )
    }
}
