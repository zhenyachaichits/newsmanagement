package com.epam.news.service.exception;

/**
 * Created by Yauhen_Chaichyts on 5/27/2016.
 */
public class ServiceException extends Exception {
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Exception cause) {
        super(message, cause);
    }
}

