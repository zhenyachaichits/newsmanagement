package com.epam.news.admin.controller;

import com.epam.news.admin.exception.ControllerException;
import com.epam.news.common.domain.Author;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.AuthorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private static final String PAGE_NAME = "authors-management";

    private static final Logger LOG = LogManager.getLogger(AuthorController.class);

    @Autowired
    private AuthorService service;

    @InitBinder
    public void binder(WebDataBinder binder) {binder.registerCustomEditor(Timestamp.class,
            new PropertyEditorSupport() {
                public void setAsText(String value) {
                    try {
                        Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(value);
                        setValue(new Timestamp(parsedDate.getTime()));
                    } catch (ParseException e) {
                        setValue(null);
                    }
                }
            });
    }

    @RequestMapping(method = RequestMethod.GET)
    public String viewAuthors(ModelMap model) throws ControllerException {
        try {
            Author author = new Author();
            List<Author> authors = service.findAll();

            model.addAttribute("authors", authors);
            model.addAttribute("authorData", author);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to find all authors", e);
        }
        return PAGE_NAME;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addAuthor(Author authorData) throws ControllerException {
        try {
            service.add(authorData);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to add author", e);
        }

        return "redirect:/authors";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateAuthor(Author authorData) throws ControllerException {
        try {
            service.update(authorData);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to update author", e);
        }

        return "redirect:/authors";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteAuthor(Author author) throws ControllerException {
        try {
            service.delete(author.getAuthorId());
        } catch (ServiceException e) {
            throw new ControllerException("Unable to delete author");
        }

        return "redirect:/authors";
    }

}
