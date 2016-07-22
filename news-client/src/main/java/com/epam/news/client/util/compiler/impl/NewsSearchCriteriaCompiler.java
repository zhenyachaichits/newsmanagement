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

        NewsSearchCriteria criteria = new NewsSearchCriteria();
        criteria.setTagIdSet(fillInIdSet(tagIds));
        criteria.setAuthorIdSet(fillInIdSet(authorIds));

        return criteria;
    }

    private Set<Long> fillInIdSet(String[] idArray) throws BeanCompilerException {
        try {
            if (idArray != null) {
                Set<Long> idSet = new HashSet<>();

                for (String id : idArray) {
                    idSet.add(Long.parseLong(id));
                }

                return idSet;
            } else {
                return null;
            }
        } catch (NumberFormatException e) {
            throw new BeanCompilerException("Invalid data in request", e);
        }
    }
}
