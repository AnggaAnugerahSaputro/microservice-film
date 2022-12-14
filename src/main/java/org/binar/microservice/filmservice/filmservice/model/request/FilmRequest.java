package org.binar.microservice.filmservice.filmservice.model.request;

import lombok.Data;
import org.binar.microservice.filmservice.filmservice.model.entity.Film;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FilmRequest {

    @NotEmpty(message = "film code is required")
    private String filmCode;

    @NotEmpty(message = "film name is required")
    private String filmName;

    @NotEmpty(message = "status film is required")
    private Boolean showStatus;

    @NotEmpty(message = "genre film is required")
    private String genre;

    @NotEmpty(message = "description film is required")
    private String description;

    @NotEmpty(message = "duration film is required")
    private String duration;

    @NotEmpty(message = "country film is required")
    private String country;

    @NotEmpty(message = "language film is required")
    private String language;

    @NotEmpty(message = "Start Date film is required")
    private LocalDate startDate;

    @NotEmpty(message = "End date film is required")
    private LocalDate endDate;

//    @NotEmpty(message = "price film is required")
    private BigDecimal price;

    public Film toFilms() {
        return Film.builder()
                .filmCode(this.filmCode)
                .filmName(this.filmName)
                .showStatus(this.showStatus)
                .genre(this.genre)
                .description(this.description)
                .duration(this.duration)
                .country(this.country)
                .language(this.language)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .price(this.price)
                .build();
            }

}
