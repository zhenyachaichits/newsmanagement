package com.epam.news.persistence.exception;

/**
 * Created by Yauhen_Chaichyts on 5/27/2016.
 */
public class DAOException extends Exception {
    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Exception cause) {
        super(message, cause);
    }
}
