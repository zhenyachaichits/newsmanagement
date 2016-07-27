package com.epam.news.client.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Zheny Chaichits on 7/24/2016.
 */
public class RequestUtil {
    private static final String REFERER_HEADER = "Referer";

    public static String getPrevious(HttpServletRequest request) {
        return request.getHeader(REFERER_HEADER);
    }
}
