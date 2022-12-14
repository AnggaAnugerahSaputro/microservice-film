package org.binar.microservice.filmservice.filmservice.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "seats")
public class Seat {

    @EmbeddedId
    @Column(name = "seatId")
    private SeatComposite seatId;

    @Column(name = "statusSeat")
    private Boolean statusSeat;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "studioId")
    private Studio studio;

}