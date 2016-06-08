package com.epam.news.persistence.util.processor.exception;


/**
 * The type Entity processor exception.
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
