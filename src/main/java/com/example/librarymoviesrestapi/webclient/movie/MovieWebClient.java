package com.example.librarymoviesrestapi.webclient.movie;

import com.example.librarymoviesrestapi.dto.MovieDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MovieWebClient {
    private static final String MOVIE_URL = "http://www.omdbapi.com/";
    private static final String API_KEY = "a8de39b0";
    private final RestTemplate restTemplate;

    public MovieWebClient() {
        this.restTemplate = new RestTemplate();
    }

    public MovieDto getRequestForMovieId(final String imdbID) {
        return getRequest("?apikey={apiKey}&i={imdbID}", MovieDto.class, API_KEY, imdbID);
    }

    public MovieDto getRequestForMovieTitle(final String title) {
        return getRequest("?apikey={apiKey}&t={title}", MovieDto.class, API_KEY, title);
    }

    private <T> T getRequest(final String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(MOVIE_URL + url, responseType, objects);
    }
}
