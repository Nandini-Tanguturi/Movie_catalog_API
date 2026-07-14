package com.mits.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mits.dto.GenreRequest;
import com.mits.dto.GenreResponse;
import com.mits.service.GenreService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/genres")
@Validated
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping
    public ResponseEntity<GenreResponse> saveGenre(@Valid @RequestBody GenreRequest request) {

        return new ResponseEntity<>(genreService.saveGenre(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GenreResponse>> findAll() {

        return new ResponseEntity<>(genreService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreResponse> findGenreById(@PathVariable Long id) {

        return new ResponseEntity<>(genreService.findGenreById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreResponse> updateGenre(@PathVariable Long id,
            @Valid @RequestBody GenreRequest request) {

        return new ResponseEntity<>(genreService.updateGenre(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGenre(@PathVariable Long id) {

        genreService.deleteGenre(id);

        return new ResponseEntity<>("Genre Deleted Successfully", HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<GenreResponse> partialUpdate(
            @PathVariable Long id,
            @RequestBody Map<String, Object> update) {

        return new ResponseEntity<>(genreService.partialUpdate(id, update), HttpStatus.OK);
    }

}