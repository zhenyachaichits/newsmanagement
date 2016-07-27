package com.epam.news.admin.controller;

import com.epam.news.admin.exception.ControllerException;
import com.epam.news.common.domain.Tag;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/tags")
public class TagController {

    private static final String TAGS_MANAGEMENT_PAGE_NAME = "tagsManagement";
    private static final String REDIRECT_TAGS_VALUE = "redirect:/tags";

    private static final String MODEL_TAGS_ATTRIBUTE = "tags";
    private static final String MODEL_TAG_DATA_ATTRIBUTE = "tagData";

    @Autowired
    private TagService service;

    @RequestMapping(method = RequestMethod.GET)
    public String viewTags(ModelMap model) throws ControllerException {
        try {
            Tag tag = new Tag();
            List<Tag> tags = service.findAll();

            model.addAttribute(MODEL_TAGS_ATTRIBUTE, tags);
            model.addAttribute(MODEL_TAG_DATA_ATTRIBUTE, tag);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to find all tags", e);
        }

        return TAGS_MANAGEMENT_PAGE_NAME;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addTag(Tag tagData) throws ControllerException {
        try {
            service.save(tagData);
        } catch (ServiceException e) {
            throw new ControllerException();
        }

        return REDIRECT_TAGS_VALUE;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteTag(Tag tagData) throws ControllerException {
        try {
            service.delete(tagData.getTagId());
        } catch (ServiceException e) {
            throw new ControllerException("Unable to delete tag", e);
        }

        return REDIRECT_TAGS_VALUE;
    }

}
