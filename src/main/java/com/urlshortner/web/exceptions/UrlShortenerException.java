package com.urlshortner.web.exceptions;

public class UrlShortenerException extends RuntimeException {
    public UrlShortenerException() {
    }

    public UrlShortenerException(String message) {
        super(message);
    }
}
