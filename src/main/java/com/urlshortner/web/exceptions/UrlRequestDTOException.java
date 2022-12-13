package com.urlshortner.web.exceptions;

public class UrlRequestDTOException extends RuntimeException {
    public UrlRequestDTOException() {
    }

    public UrlRequestDTOException(String message) {
        super(message);
    }
}
