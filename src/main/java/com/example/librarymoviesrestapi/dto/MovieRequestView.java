package com.example.librarymoviesrestapi.dto;

public interface MovieRequestView {
    Long getId();
    String getTitle();
    String getDescription();
    String getFilmGenre();
    String getDirector();
    String getPoster();
}
