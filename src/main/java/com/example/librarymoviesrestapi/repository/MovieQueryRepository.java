package com.example.librarymoviesrestapi.repository;

import com.example.librarymoviesrestapi.dto.MovieRequestView;
import com.example.librarymoviesrestapi.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MovieQueryRepository extends JpaRepository<Movie, Long> {
    Collection<MovieRequestView> findAllBy();
}
