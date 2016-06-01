package com.epam.news.domain.criteria;

import java.util.Set;

/**
 * Created by Yauhen_Chaichyts on 6/1/2016.
 */
public class NewsSearchCriteria {
    private Set<Long> authorIdSet;
    private Set<Long> tagIdSet;

    public Set<Long> getAuthorIdSet() {
        return authorIdSet;
    }

    public void setAuthorIdSet(Set<Long> authorIdSet) {
        this.authorIdSet = authorIdSet;
    }

    public Set<Long> getTagIdSet() {
        return tagIdSet;
    }

    public void setTagIdSet(Set<Long> tagIdSet) {
        this.tagIdSet = tagIdSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsSearchCriteria that = (NewsSearchCriteria) o;

        if (authorIdSet != null ? !authorIdSet.equals(that.authorIdSet) : that.authorIdSet != null) return false;
        return tagIdSet != null ? tagIdSet.equals(that.tagIdSet) : that.tagIdSet == null;

    }

    @Override
    public int hashCode() {
        int result = authorIdSet != null ? authorIdSet.hashCode() : 0;
        result = 31 * result + (tagIdSet != null ? tagIdSet.hashCode() : 0);
        return result;
    }
}
