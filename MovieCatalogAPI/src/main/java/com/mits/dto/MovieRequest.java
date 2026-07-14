package com.mits.dto;

import java.util.Set;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRequest {

    @NotBlank(message = "Movie title is required")
    private String title;

    @NotNull(message = "Release year is required")
    private Integer releaseYear;

    @NotNull(message = "Rating is required")
    @Min(value = 0)
    @Max(value = 10)
    private Double rating;

    @NotNull(message = "Director Id is required")
    private Long directorId;

    private Set<Long> genreIds;

}