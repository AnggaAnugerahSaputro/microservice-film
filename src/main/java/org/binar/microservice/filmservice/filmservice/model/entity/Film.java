package org.binar.microservice.filmservice.filmservice.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "films")
public class Film {


    @Id
    @Column(name = "film_code", nullable = false)
    private String filmCode;

    @Column(name = "film_name", nullable = false)
    private String filmName;

    @Column(name = "show_status", nullable = false)
    private Boolean showStatus = Boolean.FALSE;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "price", nullable = false, length = 400)
    private String description;

    @Column(name = "duration", nullable = false)
    private String duration;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "language", nullable = false)
    private String language;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "price_film", nullable = false)
    private BigDecimal price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "modified_at", insertable = false)
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "film", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Schedule> schedule;

}
