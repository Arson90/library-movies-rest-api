package com.example.librarymoviesrestapi.repository;

import com.example.librarymoviesrestapi.dto.MovieView;
import com.example.librarymoviesrestapi.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface MovieQueryRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findMovieByTitle(final String title);
    Collection<MovieView> findAllBy();
}
