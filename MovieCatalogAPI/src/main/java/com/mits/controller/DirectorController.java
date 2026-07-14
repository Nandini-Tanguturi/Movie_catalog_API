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

import com.mits.dto.DirectorRequest;
import com.mits.dto.DirectorResponse;
import com.mits.service.DirectorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/directors")
@Validated
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @PostMapping
    public ResponseEntity<DirectorResponse> saveDirector(@Valid @RequestBody DirectorRequest request) {

        return new ResponseEntity<>(directorService.saveDirector(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DirectorResponse>> findAll() {

        return new ResponseEntity<>(directorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorResponse> findDirectorById(@PathVariable Long id) {

        return new ResponseEntity<>(directorService.findDirectorById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectorResponse> updateDirector(@PathVariable Long id,
            @Valid @RequestBody DirectorRequest request) {

        return new ResponseEntity<>(directorService.updateDirector(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDirector(@PathVariable Long id) {

        directorService.deleteDirector(id);

        return new ResponseEntity<>("Director Deleted Successfully", HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<DirectorResponse> partialUpdate(
            @PathVariable Long id,
            @RequestBody Map<String, Object> update) {

        return new ResponseEntity<>(directorService.partialUpdate(id, update), HttpStatus.OK);
    }

}