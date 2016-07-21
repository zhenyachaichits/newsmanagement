package com.epam.news.client.util.compiler.impl;

import com.epam.news.client.exception.BeanCompilerException;
import com.epam.news.client.util.compiler.BeanCompiler;
import com.epam.news.common.domain.criteria.NewsSearchCriteria;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Component
public class NewsSearchCriteriaCompiler implements BeanCompiler<NewsSearchCriteria> {

    private static final String REQUEST_TAG_ID_SET_PARAMETER = "tagIdSet";
    private static final String REQUEST_AUTHOR_ID_SET_PARAMETER = "authorIdSet";

    @Override
    public NewsSearchCriteria constructBean(HttpServletRequest request) throws BeanCompilerException {
        String[] tagIds = request.getParameterValues(REQUEST_TAG_ID_SET_PARAMETER);
        String[] authorIds = request.getParameterValues(REQUEST_AUTHOR_ID_SET_PARAMETER);

        Set<Long> tagIdSet = new HashSet<>(tagIds.length);
        Set<Long> authorIdSet = new HashSet<>(authorIds.length);
        for (String tagId : tagIds) {
            tagIdSet.add(Long.parseLong(tagId));
        }
        for (String authorId : authorIds) {
            tagIdSet.add(Long.parseLong(authorId));
        }

        NewsSearchCriteria criteria = new NewsSearchCriteria();
        criteria.setTagIdSet(tagIdSet);
        criteria.setAuthorIdSet(authorIdSet);

        return criteria;
    }
}
