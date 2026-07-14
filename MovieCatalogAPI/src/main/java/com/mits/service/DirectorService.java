package com.mits.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mits.dto.DirectorRequest;
import com.mits.dto.DirectorResponse;
import com.mits.entity.Director;
import com.mits.exception.DirectorNotFoundException;
import com.mits.repository.DirectorRepository;

@Service
public class DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    public DirectorResponse saveDirector(DirectorRequest request) {

        Director director = convertToEntity(request);

        Director savedDirector = directorRepository.save(director);

        return convertToResponse(savedDirector);
    }

    public List<DirectorResponse> findAll() {

        List<Director> directors = directorRepository.findAll();

        List<DirectorResponse> responseList = new ArrayList<>();

        for (Director director : directors) {
            responseList.add(convertToResponse(director));
        }

        return responseList;
    }

    public DirectorResponse findDirectorById(Long id) {

        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException("Please Enter Valid Director Id"));

        return convertToResponse(director);
    }

    public DirectorResponse updateDirector(Long id, DirectorRequest request) {

        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException("Please Enter Valid Director Id"));

        director.setName(request.getName());
        director.setNationality(request.getNationality());
        director.setBirthYear(request.getBirthYear());

        Director updatedDirector = directorRepository.save(director);

        return convertToResponse(updatedDirector);
    }

    public void deleteDirector(Long id) {

        directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException("Please Enter Valid Director Id"));

        directorRepository.deleteById(id);
    }

    private Director convertToEntity(DirectorRequest request) {

        Director director = new Director();

        director.setName(request.getName());
        director.setNationality(request.getNationality());
        director.setBirthYear(request.getBirthYear());

        return director;
    }

    private DirectorResponse convertToResponse(Director director) {

        DirectorResponse response = new DirectorResponse();

        response.setId(director.getId());
        response.setName(director.getName());
        response.setNationality(director.getNationality());
        response.setBirthYear(director.getBirthYear());

        return response;
    }
    public DirectorResponse partialUpdate(Long id, Map<String, Object> update) {

        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException("Please Enter Valid Director Id"));

        if (update.containsKey("name")) {
            director.setName((String) update.get("name"));
        }

        if (update.containsKey("nationality")) {
            director.setNationality((String) update.get("nationality"));
        }

        if (update.containsKey("birthYear")) {
            director.setBirthYear((Integer) update.get("birthYear"));
        }

        Director updatedDirector = directorRepository.save(director);

        return convertToResponse(updatedDirector);
    }

}