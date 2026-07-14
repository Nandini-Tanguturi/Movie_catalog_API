package com.mits.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mits.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByDirectorId(Long directorId);

    List<Movie> findByGenresId(Long genreId);

    List<Movie> findByReleaseYear(Integer releaseYear);

    List<Movie> findByRatingGreaterThanEqual(Double rating);

}