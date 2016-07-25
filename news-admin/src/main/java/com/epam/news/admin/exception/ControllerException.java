package com.epam.news.admin.exception;

/**
 * Created by Yauhen_Chaichyts on 6/20/2016.
 */
public class ControllerException extends Exception {

    public ControllerException() {
        super();
    }

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(String message, Exception cause) {
        super(message, cause);
    }
}
