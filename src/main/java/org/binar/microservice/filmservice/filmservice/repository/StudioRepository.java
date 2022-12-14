package org.binar.microservice.filmservice.filmservice.repository;

import org.binar.microservice.filmservice.filmservice.model.entity.Film;
import org.binar.microservice.filmservice.filmservice.model.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Integer> {
    @Query("SELECT s FROM Studio s WHERE LOWER(s.studioName) LIKE LOWER(:studioName)")
    Studio findByStudioName(@Param("studioName") String studioName);
}
