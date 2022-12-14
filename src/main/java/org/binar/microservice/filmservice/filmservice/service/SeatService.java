package org.binar.microservice.filmservice.filmservice.service;

import org.binar.microservice.filmservice.filmservice.model.entity.Seat;

import java.util.List;

public interface SeatService {

    List<Seat> findAllSeat();

}
