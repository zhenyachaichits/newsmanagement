package com.epam.news.admin.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private static final String PAGE_NAME = "authors-management";

    private static final Logger LOG = LogManager.getLogger(AuthorController.class);

}
