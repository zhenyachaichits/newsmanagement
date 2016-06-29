package com.epam.news.common.service.util.pagination;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Properties;

/**
 * Created by Yauhen_Chaichyts on 6/29/2016.
 */
@Component
public class NewsPaginationUtil {

    private static final Logger LOG = LogManager.getLogger(NewsPaginationUtil.class);

    private static final String NEWS_ON_PAGE_PROPERTY_NAME = "pagination.news.onpage";
    private static final int DEFAULT_NEWS_ON_PAGE_NUMBER = 3;

    @Resource(name = "paginationProperties")
    private Properties properties;
    private int newsOnPage;

    @PostConstruct
    private void initNewsOnPageCount() {
        try {
            newsOnPage = Integer.parseInt(properties.getProperty(NEWS_ON_PAGE_PROPERTY_NAME));
        } catch (NumberFormatException e) {
            LOG.error("Error in initialization method. Default value was set", e);
            newsOnPage = DEFAULT_NEWS_ON_PAGE_NUMBER;
        }
    }

    public int getNewsOnPageNumber() {
        return newsOnPage;
    }

    public int countPages(int newsCount) {
        return (int) Math.ceil(newsCount / newsOnPage);
    }

}
