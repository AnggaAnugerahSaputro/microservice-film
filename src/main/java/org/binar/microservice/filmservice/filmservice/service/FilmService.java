package org.binar.microservice.filmservice.filmservice.service;

import org.binar.microservice.filmservice.filmservice.model.request.FilmRequest;
import org.binar.microservice.filmservice.filmservice.model.response.FilmResponse;

import java.util.List;

public interface FilmService {

    FilmResponse addFilm(FilmRequest filmRequest);
    List<FilmResponse> searchAllFilm();
    FilmResponse updateFilm(String filmCode, FilmRequest filmRequest);
    Boolean deleteFilm(String filmName);
    FilmResponse findByName(String filmName);
    FilmResponse updateIsRead(String filmCode);

    List<FilmResponse> getFilmNow();
}
