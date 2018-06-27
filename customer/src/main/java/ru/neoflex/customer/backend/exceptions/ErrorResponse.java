package ru.neoflex.customer.backend.exceptions;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Error response.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ErrorResponse extends RuntimeException{
    /**
     * Instantiates a new Error response.
     *
     * @param message the message
     */
    public ErrorResponse(String message) {
        super(message);
    }
}

