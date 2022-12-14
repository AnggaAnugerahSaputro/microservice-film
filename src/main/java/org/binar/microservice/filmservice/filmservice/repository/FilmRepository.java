package org.binar.microservice.filmservice.filmservice.repository;

import org.binar.microservice.filmservice.filmservice.model.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, String> {

    @Query("SELECT f FROM Film f WHERE LOWER(f.filmName) LIKE LOWER(:filmName)")
    Film findByFilmName(@Param("filmName") String filmName);
}
