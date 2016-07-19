package com.epam.news.common.domain.to;

import com.epam.news.common.domain.News;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Yauhen_Chaichyts on 6/21/2016.
 */
public class NewsTO {
    @Valid
    private News news;
    @NotEmpty
    private List<Long> authorIdList;
    private List<Long> tagIdList;

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public List<Long> getAuthorIdList() {
        return authorIdList;
    }

    public void setAuthorIdList(List<Long> authorIdList) {
        this.authorIdList = authorIdList;
    }

    public List<Long> getTagIdList() {
        return tagIdList;
    }

    public void setTagIdList(List<Long> tagIdList) {
        this.tagIdList = tagIdList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsTO newsTO = (NewsTO) o;

        if (news != null ? !news.equals(newsTO.news) : newsTO.news != null) return false;
        if (authorIdList != null ? !authorIdList.equals(newsTO.authorIdList) : newsTO.authorIdList != null)
            return false;
        return tagIdList != null ? tagIdList.equals(newsTO.tagIdList) : newsTO.tagIdList == null;

    }

    @Override
    public int hashCode() {
        int result = news != null ? news.hashCode() : 0;
        result = 31 * result + (authorIdList != null ? authorIdList.hashCode() : 0);
        result = 31 * result + (tagIdList != null ? tagIdList.hashCode() : 0);
        return result;
    }
}
