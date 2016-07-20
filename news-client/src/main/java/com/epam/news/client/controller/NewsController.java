package com.epam.news.client.controller;

import com.epam.news.client.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/news"})
public final class NewsController extends HttpServlet {

    private static final String CONTENT_TYPE = "text/html";
    private static final String ERROR_MESSAGE = "ERROR";
    private static final String COMMAND_QUERY = "command=";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String responseString;


        String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);

        if (commandName != null) {
            Command command = ;
            responseString = getResponseString(request, response, command, isAjax);
        } else {
            responseString = JspPageName.ERROR_PAGE;
        }

        boolean isXml = AjaxIdentifier.isResponseContextXml(response);


        if (isAjax && !isXml) {
            writeToResponse(response, responseString);
        } else if (responseString.contains(COMMAND_QUERY)) {
            response.sendRedirect(responseString);
        } else {
            forwardPage(request, response, responseString);
        }

    }

    private String getResponseString(HttpServletRequest request, HttpServletResponse response,
                                     Command command, boolean isAjax) {
        String responseString;
        try {
            responseString = command.execute(request, response);

        } catch (AccessDeniedException e) {
            responseString = ExceptionHandler.handleAccessException(e);
        } catch (InvalidSessionException e) {
            responseString = ExceptionHandler.handleSessionException(e);
        } catch (InvalidDataException e) {
            responseString = ExceptionHandler.handleDataException(e);
        } catch (CommandException e) {
            responseString = ExceptionHandler.handleCommandException(e, isAjax);
        } catch (Exception e) {
            responseString = JspPageName.ERROR_PAGE;
        }
        return responseString;
    }

    private void forwardPage(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        } else {
            writeToResponse(response, ERROR_MESSAGE);
        }
    }

    private void writeToResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType(CONTENT_TYPE);
        response.getWriter().println(message);
    }
}