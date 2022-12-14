package org.binar.microservice.filmservice.filmservice.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.binar.microservice.filmservice.filmservice.model.entity.Film;
import org.binar.microservice.filmservice.filmservice.model.request.FilmRequest;
import org.binar.microservice.filmservice.filmservice.model.response.FilmResponse;
import org.binar.microservice.filmservice.filmservice.repository.FilmRepository;
import org.binar.microservice.filmservice.filmservice.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public FilmResponse addFilm(FilmRequest filmRequest) {
        Film film = filmRequest.toFilms();
        try {
            log.info("add film success");
            filmRepository.save(film);
            return FilmResponse.build(film);
        } catch (Exception exception) {
            log.error("film not found");
            return null;
        }
    }

    @Override
    public List<FilmResponse> searchAllFilm() {
        List<Film> allFilms = filmRepository.findAll();
        List<FilmResponse> allFilmResponse = new ArrayList<>();
        for (Film film : allFilms) {
            FilmResponse filmResponse = FilmResponse.build(film);
            allFilmResponse.add(filmResponse);
        }
        return allFilmResponse;
    }


    @Override
    public FilmResponse updateFilm(String filmCode, FilmRequest filmRequest) {
        Optional<Film> isFilm = filmRepository.findById(filmCode);
        log.info("Search film By id");
        if (isFilm.isPresent()) {
            Film film = isFilm.get();
            film.setFilmName(filmRequest.getFilmName());
            film.setShowStatus(filmRequest.getShowStatus());
            film.setGenre(filmRequest.getGenre());
            film.setDescription(filmRequest.getDescription());
            film.setDuration(filmRequest.getDuration());
            film.setCountry(filmRequest.getCountry());
            film.setLanguage(filmRequest.getLanguage());
            film.setStartDate(filmRequest.getStartDate());
            film.setEndDate(filmRequest.getEndDate());
            try {
                log.info("update film success");
                filmRepository.save(film);
                return FilmResponse.build(film);
            } catch (Exception exception) {
                return null;
            }
        } else {
            throw new RuntimeException("Film is update");
        }
    }

    @Override
    public Boolean deleteFilm(String filmName) {
        Film film = filmRepository.findByFilmName(filmName);
        if(film != null) {
            log.info("delete film success");
            filmRepository.deleteById(film.getFilmCode());
            return true;
        }
        else {
            log.error("film not found");
            return false;
        }
    }

    @Override
    public FilmResponse findByName(String filmName) {
        log.info("find film by name {}", filmName);
        Film film = filmRepository.findByFilmName(filmName);
        if (film != null){
            return FilmResponse.build(film);
        }else {
            log.error("search film by name {}", filmName +" not found");
            return null;
        }
    }

    @Override
    public FilmResponse updateIsRead(String filmCode) {
        Optional<Film> isFilm = filmRepository.findById(filmCode);
        if (isFilm.isPresent()) {
            Film film = isFilm.get();
            film.setShowStatus(true);
            film.setModifiedAt(LocalDateTime.now());
            filmRepository.save(film);
            return FilmResponse.build(film);
        } else {
            return null;
        }
    }

    @Override
    public List<FilmResponse> getFilmNow() {
        List<Film> film = filmRepository.findAll().stream().filter(Film::getShowStatus).collect(Collectors.toList());
        if (film.isEmpty()){
            log.info("Movie data available");
            return new ArrayList<>();
        }else {
            log.info("movie data not available");
        }
        return film.stream().map(film1 -> {
            return FilmResponse.builder()
                    .filmCode(film1.getFilmCode())
                    .filmName(film1.getFilmName())
                    .showStatus(film1.getShowStatus())
                    .genre(film1.getGenre())
                    .description(film1.getDescription())
                    .duration(film1.getDuration())
                    .country(film1.getCountry())
                    .language(film1.getLanguage())
                    .startDate(film1.getStartDate())
                    .endDate(film1.getEndDate())
                    .build();
        }).toList();
    }
}
