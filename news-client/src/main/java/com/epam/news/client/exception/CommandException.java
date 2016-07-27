package com.epam.news.client.exception;

/**
 * Created by Yauhen_Chaichyts on 7/20/2016.
 */

public class CommandException extends Exception {
    public CommandException() {
        super();
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Exception cause) {
        super(message, cause);
    }
}
