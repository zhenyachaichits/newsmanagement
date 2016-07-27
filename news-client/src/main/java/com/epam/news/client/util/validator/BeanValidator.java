package com.epam.news.client.util.validator;

import org.springframework.stereotype.Component;

public interface BeanValidator<T> {
    boolean validate(T bean);
}
