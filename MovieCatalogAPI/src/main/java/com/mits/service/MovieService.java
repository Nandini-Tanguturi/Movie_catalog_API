package com.mits.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mits.dto.MovieRequest;
import com.mits.dto.MovieResponse;
import com.mits.entity.Director;
import com.mits.entity.Genre;
import com.mits.entity.Movie;
import com.mits.exception.DirectorNotFoundException;
import com.mits.exception.GenreNotFoundException;
import com.mits.exception.MovieNotFoundException;
import com.mits.repository.DirectorRepository;
import com.mits.repository.GenreRepository;
import com.mits.repository.MovieRepository;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private GenreRepository genreRepository;

    // Save Movie
    public MovieResponse saveMovie(MovieRequest request) {

        Director director = directorRepository.findById(request.getDirectorId())
                .orElseThrow(() -> new DirectorNotFoundException("Please Enter Valid Director Id"));

        Set<Genre> genres = new HashSet<>();

        for (Long id : request.getGenreIds()) {

            Genre genre = genreRepository.findById(id)
                    .orElseThrow(() -> new GenreNotFoundException("Please Enter Valid Genre Id"));

            genres.add(genre);
        }

        Movie movie = convertToEntity(request);

        movie.setDirector(director);
        movie.setGenres(genres);

        Movie savedMovie = movieRepository.save(movie);

        return convertToResponse(savedMovie);
    }

    // Get All Movies
    public List<MovieResponse> findAll() {

        List<Movie> movies = movieRepository.findAll();

        List<MovieResponse> responseList = new ArrayList<>();

        for (Movie movie : movies) {
            responseList.add(convertToResponse(movie));
        }

        return responseList;
    }

    // Get Movie By Id
    public MovieResponse findMovieById(Long id) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Please Enter Valid Movie Id"));

        return convertToResponse(movie);
    }

    // Update Movie
    public MovieResponse updateMovie(Long id, MovieRequest request) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Please Enter Valid Movie Id"));

        Director director = directorRepository.findById(request.getDirectorId())
                .orElseThrow(() -> new DirectorNotFoundException("Please Enter Valid Director Id"));

        Set<Genre> genres = new HashSet<>();

        for (Long genreId : request.getGenreIds()) {

            Genre genre = genreRepository.findById(genreId)
                    .orElseThrow(() -> new GenreNotFoundException("Please Enter Valid Genre Id"));

            genres.add(genre);
        }

        movie.setTitle(request.getTitle());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setRating(request.getRating());
        movie.setDirector(director);
        movie.setGenres(genres);

        Movie updatedMovie = movieRepository.save(movie);

        return convertToResponse(updatedMovie);
    }

    // Delete Movie
    public void deleteMovie(Long id) {

        movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Please Enter Valid Movie Id"));

        movieRepository.deleteById(id);
    }

    // Convert DTO to Entity
    private Movie convertToEntity(MovieRequest request) {

        Movie movie = new Movie();

        movie.setTitle(request.getTitle());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setRating(request.getRating());

        return movie;
    }

    // Convert Entity to DTO
    private MovieResponse convertToResponse(Movie movie) {

        MovieResponse response = new MovieResponse();

        response.setId(movie.getId());
        response.setTitle(movie.getTitle());
        response.setReleaseYear(movie.getReleaseYear());
        response.setRating(movie.getRating());

        response.setDirectorName(movie.getDirector().getName());

        Set<String> genreNames = new HashSet<>();

        for (Genre genre : movie.getGenres()) {
            genreNames.add(genre.getName());
        }

        response.setGenres(genreNames);

        return response;
    }
 // Add Genre To Movie
    public MovieResponse addGenreToMovie(Long movieId, Long genreId) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Please Enter Valid Movie Id"));

        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new GenreNotFoundException("Please Enter Valid Genre Id"));

        movie.getGenres().add(genre);

        Movie updatedMovie = movieRepository.save(movie);

        return convertToResponse(updatedMovie);
    }

    // Remove Genre From Movie
    public MovieResponse removeGenreFromMovie(Long movieId, Long genreId) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Please Enter Valid Movie Id"));

        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new GenreNotFoundException("Please Enter Valid Genre Id"));

        movie.getGenres().remove(genre);

        Movie updatedMovie = movieRepository.save(movie);

        return convertToResponse(updatedMovie);
    }

    // Find Movies By Director
    public List<MovieResponse> findMoviesByDirector(Long directorId) {

        directorRepository.findById(directorId)
                .orElseThrow(() -> new DirectorNotFoundException("Please Enter Valid Director Id"));

        List<Movie> movies = movieRepository.findByDirectorId(directorId);

        List<MovieResponse> responseList = new ArrayList<>();

        for (Movie movie : movies) {
            responseList.add(convertToResponse(movie));
        }

        return responseList;
    }

    // Find Movies By Genre
    public List<MovieResponse> findMoviesByGenre(Long genreId) {

        genreRepository.findById(genreId)
                .orElseThrow(() -> new GenreNotFoundException("Please Enter Valid Genre Id"));

        List<Movie> movies = movieRepository.findByGenresId(genreId);

        List<MovieResponse> responseList = new ArrayList<>();

        for (Movie movie : movies) {
            responseList.add(convertToResponse(movie));
        }

        return responseList;
    }

    // Find Movies By Release Year
    public List<MovieResponse> findMoviesByYear(Integer year) {

        List<Movie> movies = movieRepository.findByReleaseYear(year);

        List<MovieResponse> responseList = new ArrayList<>();

        for (Movie movie : movies) {
            responseList.add(convertToResponse(movie));
        }

        return responseList;
    }

    // Find Movies By Rating
    public List<MovieResponse> findMoviesByRating(Double rating) {

        List<Movie> movies = movieRepository.findByRatingGreaterThanEqual(rating);

        List<MovieResponse> responseList = new ArrayList<>();

        for (Movie movie : movies) {
            responseList.add(convertToResponse(movie));
        }

        return responseList;
    }
    public MovieResponse partialUpdate(Long id, Map<String, Object> update) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Please Enter Valid Movie Id"));

        if (update.containsKey("title")) {
            movie.setTitle((String) update.get("title"));
        }

        if (update.containsKey("releaseYear")) {
            movie.setReleaseYear((Integer) update.get("releaseYear"));
        }

        if (update.containsKey("rating")) {
            movie.setRating((Double) update.get("rating"));
        }

        Movie updatedMovie = movieRepository.save(movie);

        return convertToResponse(updatedMovie);
    }
}