package com.epam.news.admin.controller;

import com.epam.news.admin.exception.ControllerException;
import com.epam.news.admin.util.DateEditorSupport;
import com.epam.news.common.domain.Author;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private static final String PAGE_NAME = "authorsManagement";
    private static final String REDIRECT_AUTHORS_VALUE = "redirect:/authors";

    private static final String MODEL_AUTHORS_ATTRIBUTE = "authors";
    private static final String MODEL_AUTHOR_DATA_ATTRIBUTE = "authorData";

    @Autowired
    private AuthorService service;

    @InitBinder
    public void binder(WebDataBinder binder) {
        binder.registerCustomEditor(Timestamp.class, new DateEditorSupport());
    }

    @RequestMapping(method = RequestMethod.GET)
    public String viewAuthors(ModelMap model) throws ControllerException {
        try {
            Author author = new Author();
            List<Author> authors = service.findAll();

            model.addAttribute(MODEL_AUTHORS_ATTRIBUTE, authors);
            model.addAttribute(MODEL_AUTHOR_DATA_ATTRIBUTE, author);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to find all authors", e);
        }
        return PAGE_NAME;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAuthor(Author authorData) throws ControllerException {
        try {
            service.save(authorData);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to save author", e);
        }

        return REDIRECT_AUTHORS_VALUE;
    }

    @RequestMapping(value = "/expire", method = RequestMethod.POST)
    public String expireAuthor(Author author) throws ControllerException {
        try {
            service.expireAuthor(author.getAuthorId());
        } catch (ServiceException e) {
            throw new ControllerException("Unable to delete author");
        }

        return REDIRECT_AUTHORS_VALUE;
    }

}
