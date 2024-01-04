package com.example.librarymoviesrestapi.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String message) {
        super(message);
    }

    public static MovieNotFoundException createForMovieId(final String imdbID) {
        return new MovieNotFoundException(String.format("Movie with id: %s not found", imdbID));
    }
    public static MovieNotFoundException createForMovieTitle(final String title) {
        return new MovieNotFoundException(String.format("Movie %s not found", title));
    }
}
