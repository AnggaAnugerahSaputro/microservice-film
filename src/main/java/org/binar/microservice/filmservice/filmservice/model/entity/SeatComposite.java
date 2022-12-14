package org.binar.microservice.filmservice.filmservice.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;



@Getter
@Setter
@Embeddable
public class SeatComposite implements Serializable {

    @Column(name = "seatRow", nullable = false)
    private Character seatRow;

    @Column(name = "seatNumber", nullable = false)
    private Integer seatNumber;

}
