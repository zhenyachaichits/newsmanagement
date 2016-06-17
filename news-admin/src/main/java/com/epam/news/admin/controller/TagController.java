package com.epam.news.admin.controller;

import com.epam.news.common.domain.Tag;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.TagService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/tags")
public class TagController {

    private static final String PAGE_NAME = "tags-management";

    private static final Logger LOG = LogManager.getLogger(TagController.class);

    @Autowired
    private TagService service;

    @RequestMapping(method = RequestMethod.GET)
    public String viewTags(ModelMap model) {
        try {
            Tag tag = new Tag();
            List<Tag> tags = service.findAll();

            model.addAttribute("tags", tags);
            model.addAttribute("newTag", tag);
        } catch (ServiceException e) {
            LOG.error("Error in finding all tags operation", e);
        }

        return PAGE_NAME;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addTag(@ModelAttribute("newTag") Tag tag) {
        try {
            service.add(tag);
        } catch (ServiceException e) {
            LOG.error("Error in adding tag operation", e);
        }

        return "redirect:/tags";
    }

}
