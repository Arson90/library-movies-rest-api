package com.example.librarymoviesrestapi.service;

import com.example.librarymoviesrestapi.dto.MovieDto;
import com.example.librarymoviesrestapi.dto.MovieView;
import com.example.librarymoviesrestapi.entity.Movie;
import com.example.librarymoviesrestapi.entity.MovieMapper;
import com.example.librarymoviesrestapi.exception.MovieAlreadyExistsException;
import com.example.librarymoviesrestapi.exception.MovieNotFoundException;
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
    public MovieDto getMovieById(final String imdbID) {
        final MovieDto movieDto = this.movieWebClient.getRequestForMovieId(imdbID);
        //I assume if there is no title then movie doesn't exists
        if (movieDto.getTitle() == null) {
            throw MovieNotFoundException.createForMovieId(imdbID);
        }
        return movieDto;
    }

    @Override
    public MovieDto getMovieByTitle(final String title) {
        final MovieDto movieDto = this.movieWebClient.getRequestForMovieTitle(title);
        //I assume if there is no title then movie doesn't exists
        if (movieDto.getTitle() == null) {
            throw MovieNotFoundException.createForMovieTitle(title);
        }
        return movieDto;
    }

    @Override
    public void save(final String imdbID) {
        final MovieDto movieDto = this.movieWebClient.getRequestForMovieId(imdbID);
        final String title = movieDto.getTitle();
        if (this.queryRepository.findMovieByTitle(title).isPresent()){
            throw MovieAlreadyExistsException.createForMovieTitle(title);
        } else {
            final Movie movie = MovieMapper.convertMovieDtoToMovie(movieDto);
            this.commandRepository.save(movie);
        }
    }

    @Override
    public Collection<MovieView> getFavoriteMovies() {
        return this.queryRepository.findAllBy();
    }
}
