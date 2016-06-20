package com.epam.news.admin.controller;

import com.epam.news.admin.exception.ControllerException;
import com.epam.news.common.domain.Tag;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.TagService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String viewTags(ModelMap model) throws ControllerException {
        try {
            Tag tag = new Tag();
            List<Tag> tags = service.findAll();

            model.addAttribute("tags", tags);
            model.addAttribute("tagData", tag);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to find all tags", e);
        }

        return PAGE_NAME;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addTag(Tag tagData, BindingResult result, ModelMap mode) throws ControllerException {
        try {
            service.add(tagData);
        } catch (ServiceException e) {
            LOG.error("Error in adding tag operation", e);
            throw new ControllerException();
        }

        return "redirect:/tags";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String editTag(Tag tagData) {
        try {
            service.update(tagData);
        } catch (ServiceException e) {
            LOG.error("Error in editing tag operation", e);
        }

        return "redirect:/tags";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteTag(Tag tagData) {
        try {
            service.delete(tagData.getTagId());
        } catch (ServiceException e) {
            LOG.error("Error in deleting tag operation", e);
        }

        return "redirect:/tags";
    }

}
