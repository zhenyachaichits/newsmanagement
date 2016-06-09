package com.epam.news.domain.criteria;

import java.util.Set;


/**
 * The type News Search Criteria. Contains information about authors and tags for news search
 */
public class NewsSearchCriteria {
    private Set<Long> authorIdSet;
    private Set<Long> tagIdSet;

    /**
     * Gets author id set.
     *
     * @return the author id set
     */
    public Set<Long> getAuthorIdSet() {
        return authorIdSet;
    }

    /**
     * Sets author id set.
     *
     * @param authorIdSet the author id set
     */
    public void setAuthorIdSet(Set<Long> authorIdSet) {
        this.authorIdSet = authorIdSet;
    }

    /**
     * Gets tag id set.
     *
     * @return the tag id set
     */
    public Set<Long> getTagIdSet() {
        return tagIdSet;
    }

    /**
     * Sets tag id set.
     *
     * @param tagIdSet the tag id set
     */
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
