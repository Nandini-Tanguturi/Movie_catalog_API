package com.mits.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mits.dto.GenreRequest;
import com.mits.dto.GenreResponse;
import com.mits.entity.Genre;
import com.mits.exception.GenreNotFoundException;
import com.mits.repository.GenreRepository;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public GenreResponse saveGenre(GenreRequest request) {

        Genre genre = convertToEntity(request);

        Genre savedGenre = genreRepository.save(genre);

        return convertToResponse(savedGenre);
    }

    public List<GenreResponse> findAll() {

        List<Genre> genres = genreRepository.findAll();

        List<GenreResponse> responseList = new ArrayList<>();

        for (Genre genre : genres) {
            responseList.add(convertToResponse(genre));
        }

        return responseList;
    }

    public GenreResponse findGenreById(Long id) {

        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException("Please Enter Valid Genre Id"));

        return convertToResponse(genre);
    }

    public GenreResponse updateGenre(Long id, GenreRequest request) {

        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException("Please Enter Valid Genre Id"));

        genre.setName(request.getName());

        Genre updatedGenre = genreRepository.save(genre);

        return convertToResponse(updatedGenre);
    }

    public void deleteGenre(Long id) {

        genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException("Please Enter Valid Genre Id"));

        genreRepository.deleteById(id);
    }

    private Genre convertToEntity(GenreRequest request) {

        Genre genre = new Genre();

        genre.setName(request.getName());

        return genre;
    }

    private GenreResponse convertToResponse(Genre genre) {

        GenreResponse response = new GenreResponse();

        response.setId(genre.getId());
        response.setName(genre.getName());

        return response;
    }
    public GenreResponse partialUpdate(Long id, Map<String, Object> update) {

        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException("Please Enter Valid Genre Id"));

        if (update.containsKey("name")) {
            genre.setName((String) update.get("name"));
        }

        Genre updatedGenre = genreRepository.save(genre);

        return convertToResponse(updatedGenre);
    }

}