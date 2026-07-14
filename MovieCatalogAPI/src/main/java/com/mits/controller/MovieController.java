package com.mits.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mits.dto.MovieRequest;
import com.mits.dto.MovieResponse;
import com.mits.service.MovieService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/movies")
@Validated
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponse> saveMovie(@Valid @RequestBody MovieRequest request) {

        return new ResponseEntity<>(movieService.saveMovie(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll() {

        return new ResponseEntity<>(movieService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findMovieById(@PathVariable Long id) {

        return new ResponseEntity<>(movieService.findMovieById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable Long id,
            @Valid @RequestBody MovieRequest request) {

        return new ResponseEntity<>(movieService.updateMovie(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {

        movieService.deleteMovie(id);

        return new ResponseEntity<>("Movie Deleted Successfully", HttpStatus.OK);
    }

    @PutMapping("/{movieId}/genres/{genreId}")
    public ResponseEntity<MovieResponse> addGenreToMovie(@PathVariable Long movieId,
            @PathVariable Long genreId) {

        return new ResponseEntity<>(movieService.addGenreToMovie(movieId, genreId), HttpStatus.OK);
    }

    @DeleteMapping("/{movieId}/genres/{genreId}")
    public ResponseEntity<MovieResponse> removeGenreFromMovie(@PathVariable Long movieId,
            @PathVariable Long genreId) {

        return new ResponseEntity<>(movieService.removeGenreFromMovie(movieId, genreId), HttpStatus.OK);
    }

    @GetMapping("/director/{directorId}")
    public ResponseEntity<List<MovieResponse>> findMoviesByDirector(@PathVariable Long directorId) {

        return new ResponseEntity<>(movieService.findMoviesByDirector(directorId), HttpStatus.OK);
    }

    @GetMapping("/genre/{genreId}")
    public ResponseEntity<List<MovieResponse>> findMoviesByGenre(@PathVariable Long genreId) {

        return new ResponseEntity<>(movieService.findMoviesByGenre(genreId), HttpStatus.OK);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<MovieResponse>> findMoviesByYear(@PathVariable Integer year) {

        return new ResponseEntity<>(movieService.findMoviesByYear(year), HttpStatus.OK);
    }

    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<MovieResponse>> findMoviesByRating(@PathVariable Double rating) {

        return new ResponseEntity<>(movieService.findMoviesByRating(rating), HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<MovieResponse> partialUpdate(
            @PathVariable Long id,
            @RequestBody Map<String, Object> update) {

        return new ResponseEntity<>(movieService.partialUpdate(id, update), HttpStatus.OK);
    }

}