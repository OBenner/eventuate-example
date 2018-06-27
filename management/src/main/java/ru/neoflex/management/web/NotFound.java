package ru.neoflex.management.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The  Not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFound extends RuntimeException {

    /**
     * Instantiates a new Not found.
     *
     * @param message the message
     */
    public NotFound(String message) {
        super(message);
    }
}
