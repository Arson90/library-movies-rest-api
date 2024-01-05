package com.example.librarymoviesrestapi.webclient.movie;

import com.example.librarymoviesrestapi.dto.MovieDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class MovieWebClientTest {
    private final String VALID_API_KEY = "a8de39b0";
    private final String VALID_IMDB_ID = "tt0120338";
    private final String INVALID_TITLE = "Test1234";
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private MovieWebClient webClient;

    @Test
    void givenValidImdbId_whenGetIsCalled_shouldReturnTheSameTitles() {
        //GIVEN
        final MovieDto movieDto = MovieDto.builder()
                .title("Titanic")
                .plot("Plot")
                .genre("Genre")
                .director("Director")
                .poster("Poster")
                .build();

        //WHEN
        Mockito.lenient().when(restTemplate.getForObject("http://www.omdbapi.com/?apikey={apiKey}&i={imdbID}", MovieDto.class,  VALID_API_KEY, VALID_IMDB_ID))
                .thenReturn(movieDto);
        final MovieDto result = webClient.getRequestForMovieId(VALID_IMDB_ID);

        //THEN
        assertEquals(movieDto.getTitle(), result.getTitle());
    }

    @Test
    void givenInvalidTitle_whenGetIsCalled_shouldReturnNullAsATitle() {
        //GIVEN
        final MovieDto movieDto = new MovieDto();

        //WHEN
        Mockito.lenient().when(restTemplate.getForObject("http://www.omdbapi.com/?apikey={apiKey}&i={title}", MovieDto.class,  VALID_API_KEY, INVALID_TITLE))
                .thenReturn(movieDto);
        final MovieDto result = webClient.getRequestForMovieTitle(INVALID_TITLE);

        //THEN
        assertEquals(movieDto.getTitle(), result.getTitle());
        assertNull(result.getTitle());
    }
}