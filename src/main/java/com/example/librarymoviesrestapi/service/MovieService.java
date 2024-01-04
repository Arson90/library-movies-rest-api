package com.example.librarymoviesrestapi.service;

import com.example.librarymoviesrestapi.dto.MovieView;
import com.example.librarymoviesrestapi.entity.Movie;

import java.util.Collection;

public interface MovieService {
    MovieView getMovieById(final Long id);
    MovieView getMovieByTitle(final String title);
    void save(final Movie movie);
    Collection<MovieView> getFavoriteMovies();
}
