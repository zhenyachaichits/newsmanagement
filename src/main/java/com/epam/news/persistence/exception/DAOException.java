package com.epam.news.persistence.exception;


/**
 * The type Dao exception.
 */
public class DAOException extends Exception {
    /**
     * Instantiates a new Dao exception.
     */
    public DAOException() {
        super();
    }

    /**
     * Instantiates a new Dao exception.
     *
     * @param message the message
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Dao exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public DAOException(String message, Exception cause) {
        super(message, cause);
    }
}
