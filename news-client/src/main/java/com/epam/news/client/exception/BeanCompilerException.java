package com.epam.news.client.exception;

/**
 * Created by Yauhen_Chaichyts on 7/21/2016.
 */
public class BeanCompilerException extends Exception {

    public BeanCompilerException() {
        super();
    }

    public BeanCompilerException(String message) {
        super(message);
    }

    public BeanCompilerException(String message, Exception cause) {
        super(message, cause);
    }
}
