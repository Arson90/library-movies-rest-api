package com.example.librarymoviesrestapi.entity;

import com.example.librarymoviesrestapi.dto.MovieDto;

public final class MovieMapper {
    private MovieMapper() {
    }

    public static Movie convertMovieDtoToMovie(final MovieDto request) {
        return Movie.builder()
                .title(request.getTitle())
                .description(request.getPlot())
                .filmGenre(request.getGenre())
                .director(request.getDirector())
                .poster(request.getPoster())
                .build();
    }
}
