package com.mits.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieResponse {

    private Long id;

    private String title;

    private Integer releaseYear;

    private Double rating;

    private String directorName;

    private Set<String> genres;

}