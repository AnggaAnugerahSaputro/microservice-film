package org.binar.microservice.filmservice.filmservice.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "studio")
public class Studio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "studioId")
    private Integer studioId;

    @Column(name = "studioName")
    private String studioName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "studioId")
    private Set<Seat> seats;
}
