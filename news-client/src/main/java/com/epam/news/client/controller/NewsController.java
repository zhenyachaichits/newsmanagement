package com.epam.news.client.controller;

import com.epam.news.client.controller.command.Command;
import com.epam.news.client.exception.CommandException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
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
    private static final String REQUEST_COMMAND_NAME_PARAMETER = "command";
    private static final String CONTEXT_PATH = "client-context.xml";

    private ApplicationContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        context = new ClassPathXmlApplicationContext(CONTEXT_PATH);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandName = request.getParameter(REQUEST_COMMAND_NAME_PARAMETER);

        if (commandName != null) {
            Command command = (Command) context.getBean(commandName);
            try {
                String responseString = command.execute(request);

                if (responseString.contains(COMMAND_QUERY)) {
                    response.sendRedirect(responseString);
                } else {
                    forwardPage(request, response, responseString);
                }
            } catch (CommandException e) {
                //response.sendError(505);
                // TODO: 7/21/2016 refactor
                throw new ServletException();
            }
        }
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