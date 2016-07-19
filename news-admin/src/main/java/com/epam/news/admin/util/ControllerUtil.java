package com.epam.news.admin.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yauhen_Chaichyts on 7/19/2016.
 */
public class ControllerUtil {

    private static final String REDIRECT_ADDITION = "redirect:";
    private static final String REFERER_HEADER = "Referer";

    public static String redirectToPrevious(HttpServletRequest request) {
        String previous = request.getHeader(REFERER_HEADER);

        return REDIRECT_ADDITION + previous;
    }
}
