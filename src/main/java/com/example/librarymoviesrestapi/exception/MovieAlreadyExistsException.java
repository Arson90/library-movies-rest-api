package com.example.librarymoviesrestapi.exception;

public class MovieAlreadyExistsException extends RuntimeException {
    public MovieAlreadyExistsException(String message) {
        super(message);
    }

    public static MovieAlreadyExistsException createForMovieTitle(final String title) {
        return new MovieAlreadyExistsException(String.format("Movie %s already exists", title));
    }
}
