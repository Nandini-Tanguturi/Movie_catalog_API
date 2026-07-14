package com.mits.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DirectorRequest {

    @NotBlank(message = "Director name is required")
    private String name;

    private String nationality;

    private Integer birthYear;

}