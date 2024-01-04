package com.example.librarymoviesrestapi.controller;

import com.example.librarymoviesrestapi.dto.MovieDto;
import com.example.librarymoviesrestapi.dto.MovieView;
import com.example.librarymoviesrestapi.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/v1/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/id/{imdbID}")
    ResponseEntity<MovieDto> getById(@PathVariable final String imdbID) {
        final MovieDto movie = this.movieService.getMovieById(imdbID);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/{title}")
    ResponseEntity<MovieDto> getByTitle(@PathVariable("title") final String title) {
        final MovieDto movie = this.movieService.getMovieByTitle(title);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/favorite-movies")
    ResponseEntity<Collection<MovieView>> getFavoriteMovies() {
        Collection<MovieView> favoriteMovies = this.movieService.getFavoriteMovies();
        if (!favoriteMovies.isEmpty()) {
            return ResponseEntity.ok(favoriteMovies);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{imdbID}")
    ResponseEntity<Void> saveFavoriteMovieById(@PathVariable final String imdbID) {
        this.movieService.save(imdbID);
        return ResponseEntity.ok().build();
    }
}
