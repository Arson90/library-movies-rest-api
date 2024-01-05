package com.example.librarymoviesrestapi.service;

import com.example.librarymoviesrestapi.dto.MovieDto;
import com.example.librarymoviesrestapi.entity.Movie;
import com.example.librarymoviesrestapi.entity.MovieMapper;
import com.example.librarymoviesrestapi.exception.MovieAlreadyExistsException;
import com.example.librarymoviesrestapi.exception.MovieNotFoundException;
import com.example.librarymoviesrestapi.repository.MovieCommandRepository;
import com.example.librarymoviesrestapi.repository.MovieQueryRepository;
import com.example.librarymoviesrestapi.webclient.movie.MovieWebClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {
    private final String INVALID_IMDB_ID = "0001";
    private final String VALID_IMDB_ID = "tt0120338";
    private final String VALID_TITLE = "Titanic";
    private MovieDto movieDto;
    private Movie movie;

    @Mock
    private MovieWebClient webClient;
    @Mock
    private MovieCommandRepository commandRepository;
    @Mock
    private MovieQueryRepository queryRepository;
    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeEach
    void init() {
        this.movieDto = MovieDto.builder()
                .title("Titanic")
                .plot("Plot")
                .genre("Genre")
                .director("Director")
                .poster("Poster")
                .build();
        this.movie = MovieMapper.convertMovieDtoToMovie(movieDto);
    }

    @Test
    void givenInvalidImdbId_whenGetRequestIsCalled_shouldInvokedMovieNotFoundException() {
        //GIVEN
        final MovieDto emptyMovieDto = new MovieDto();

        //WHEN
        Mockito.lenient().when(this.webClient.getRequestForMovieId(INVALID_IMDB_ID)).thenReturn(emptyMovieDto);

        //THEN
        assertThrows(MovieNotFoundException.class, () -> this.movieService.getMovieById(INVALID_IMDB_ID));
    }

    @Test
    void givenValidTitle_whenGetRequestIsCalled_shouldReturnTheSameTitle() {
        //WHEN
        Mockito.lenient().when(this.webClient.getRequestForMovieTitle(VALID_TITLE)).thenReturn(movieDto);
        final MovieDto result = this.movieService.getMovieByTitle(movieDto.getTitle());

        //THEN
        assertEquals(movieDto.getTitle(), result.getTitle());
    }

    @Test
    void givenMovie_whenTryToSave_shouldInvokedSaveMethod() {
        //WHEN
        Mockito.lenient().when(this.webClient.getRequestForMovieId(VALID_IMDB_ID)).thenReturn(movieDto);
        Mockito.lenient().when(this.queryRepository.findMovieByTitle(movieDto.getTitle())).thenReturn(Optional.empty());
        Mockito.lenient().when(this.commandRepository.save(Mockito.any(Movie.class))).thenReturn(movie);
        this.movieService.save(VALID_IMDB_ID);

        //THEN
        Mockito.verify(this.commandRepository, times(1)).save(Mockito.any(Movie.class));
    }

    @Test
    void givenExistsMovie_whenTryToSave_shouldInvokedMovieAlreadyExistsException() {
        //WHEN
        Mockito.lenient().when(this.webClient.getRequestForMovieId(VALID_IMDB_ID)).thenReturn(movieDto);
        Mockito.lenient().when(this.queryRepository.findMovieByTitle(movieDto.getTitle())).thenReturn(Optional.ofNullable(movie));

        //THEN
        assertThrows(MovieAlreadyExistsException.class, () ->this.movieService.save(VALID_IMDB_ID));
    }
}