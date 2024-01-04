package com.example.librarymoviesrestapi.service;

import com.example.librarymoviesrestapi.dto.MovieDto;
import com.example.librarymoviesrestapi.dto.MovieView;
import com.example.librarymoviesrestapi.entity.Movie;

import java.util.Collection;

public interface MovieService {
    MovieDto getMovieById(final String imdbID);
    MovieDto getMovieByTitle(final String title);
    void save(final String imdbID);
    Collection<MovieView> getFavoriteMovies();
}
