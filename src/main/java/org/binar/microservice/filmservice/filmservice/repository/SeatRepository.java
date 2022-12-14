package org.binar.microservice.filmservice.filmservice.repository;

import org.binar.microservice.filmservice.filmservice.model.entity.SeatComposite;
import org.binar.microservice.filmservice.filmservice.model.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, SeatComposite> {
}
