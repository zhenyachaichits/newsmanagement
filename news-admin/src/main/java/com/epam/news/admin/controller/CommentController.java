package com.epam.news.admin.controller;

import com.epam.news.admin.exception.ControllerException;
import com.epam.news.admin.util.ControllerUtil;
import com.epam.news.common.domain.Comment;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public String addNewsComment(Comment commentData, HttpServletRequest request) throws ControllerException {
        try {
            commentService.save(commentData);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to add news comment data", e);
        }

        return ControllerUtil.redirectToPrevious(request);
    }

    @RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
    public String deleteNewsComment(@RequestParam Long id, HttpServletRequest request) throws ControllerException {
        try {
            commentService.delete(id);
        } catch (ServiceException e) {
            throw new ControllerException("Unable to delete news comment data", e);
        }

        return ControllerUtil.redirectToPrevious(request);
    }
}
