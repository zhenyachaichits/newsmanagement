package com.epam.news.admin.controller;

import com.epam.news.common.domain.User;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {

    private static final String LOGIN_PAGE_NAME = "login";
    private static final String REDIRECT_LOGIN_VALUE = "redirect:/login";

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String viewLoginPage(ModelMap model) {
        return LOGIN_PAGE_NAME;
    }

}
