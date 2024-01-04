package com.example.librarymoviesrestapi.service;

import com.example.librarymoviesrestapi.dto.MovieView;
import com.example.librarymoviesrestapi.entity.Movie;
import com.example.librarymoviesrestapi.repository.MovieCommandRepository;
import com.example.librarymoviesrestapi.repository.MovieQueryRepository;
import com.example.librarymoviesrestapi.webclient.movie.MovieWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{
    private final MovieWebClient movieWebClient;
    private final MovieCommandRepository commandRepository;
    private final MovieQueryRepository queryRepository;

    @Override
    public MovieView getMovieById(Long id) {
        return null;
    }

    @Override
    public MovieView getMovieByTitle(String title) {
        return null;
    }

    @Override
    public void save(Movie movie) {

    }

    @Override
    public Collection<MovieView> getFavoriteMovies() {
        return null;
    }
}
