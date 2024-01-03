package com.example.librarymoviesrestapi.entity;

import com.example.librarymoviesrestapi.dto.MovieRequestDto;

public final class MovieMapper {
    private MovieMapper() {
    }

    public static Movie convertMovieRequestDtoToMovie(final MovieRequestDto request) {
        return Movie.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .filmGenre(request.getFilmGenre())
                .director(request.getDirector())
                .poster(request.getPoster())
                .build();
    }
}
