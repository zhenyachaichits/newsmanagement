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
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET)
    public String viewRegistration(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);

        return "create-user";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createUser(@ModelAttribute("user") User user) {
        try {
            service.add(user);
        } catch (ServiceException e) {
            LOG.error("Error in register operation", e);
        }
        return "hello";
    }


}
