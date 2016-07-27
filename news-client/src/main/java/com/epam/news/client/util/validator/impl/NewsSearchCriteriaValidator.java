package com.epam.news.client.util.validator.impl;

import com.epam.news.client.util.validator.BeanValidator;
import com.epam.news.common.domain.criteria.NewsSearchCriteria;
import org.springframework.stereotype.Component;


@Component
public class NewsSearchCriteriaValidator implements BeanValidator<NewsSearchCriteria> {
    @Override
    public boolean validate(NewsSearchCriteria criteria) {
        if (criteria.getAuthorIdSet() == null || criteria.getTagIdSet() == null) {
            return false;
        }

        return true;
    }
}
