package com.epam.news.persistence.util.processor.exception;

/**
 * Created by Zheny Chaichits on 28.05.2016.
 */
public class EntityProcessorException extends Exception {
    public EntityProcessorException() {
        super();
    }

    public EntityProcessorException(String message) {
        super(message);
    }

    public EntityProcessorException(String message, Exception cause) {
        super(message, cause);
    }
}
