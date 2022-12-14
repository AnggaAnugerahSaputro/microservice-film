package org.binar.microservice.filmservice.filmservice.model.response;

import lombok.Builder;
import lombok.Data;
import org.binar.microservice.filmservice.filmservice.model.entity.Film;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class FilmResponse {

    private String filmCode;
    private String filmName;
    private Boolean showStatus;
    private String genre;
    private String description;
    private String duration;
    private String country;
    private String language;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;

    public static FilmResponse build(Film film) {
        return FilmResponse.builder()
                .filmCode(film.getFilmCode())
                .filmName(film.getFilmName())
                .showStatus(film.getShowStatus())
                .genre(film.getGenre())
                .description(film.getDescription())
                .duration(film.getDuration())
                .country(film.getCountry())
                .language(film.getLanguage())
                .startDate(film.getStartDate())
                .endDate(film.getEndDate())
                .price(film.getPrice())
                .build();
    }
}
