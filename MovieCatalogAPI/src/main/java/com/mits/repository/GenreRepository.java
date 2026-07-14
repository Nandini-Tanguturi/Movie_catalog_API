package com.mits.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mits.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{

}