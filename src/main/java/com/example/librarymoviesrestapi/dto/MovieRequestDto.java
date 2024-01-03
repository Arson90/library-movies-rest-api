package com.example.librarymoviesrestapi.dto;

import lombok.Getter;

@Getter
public class MovieRequestDto {
    private String title;
    private String description;
    private String filmGenre;
    private String director;
    private String poster;
}
