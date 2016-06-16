package com.epam.news.common.exception;


/**
 * The EntityProcessor exception.
 * Can be thrown in any method of EntityProcessor implementations.
 */
public class EntityProcessorException extends Exception {
    /**
     * Instantiates a new Entity processor exception.
     */
    public EntityProcessorException() {
        super();
    }

    /**
     * Instantiates a new Entity processor exception.
     *
     * @param message the message
     */
    public EntityProcessorException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Entity processor exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public EntityProcessorException(String message, Exception cause) {
        super(message, cause);
    }
}
