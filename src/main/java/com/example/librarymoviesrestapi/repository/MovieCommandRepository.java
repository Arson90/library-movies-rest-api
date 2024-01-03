package com.example.librarymoviesrestapi.repository;

import com.example.librarymoviesrestapi.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCommandRepository extends JpaRepository<Movie, Long> {

}
