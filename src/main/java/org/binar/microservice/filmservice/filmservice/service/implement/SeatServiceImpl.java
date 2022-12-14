package org.binar.microservice.filmservice.filmservice.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.binar.microservice.filmservice.filmservice.model.entity.Seat;
import org.binar.microservice.filmservice.filmservice.repository.SeatRepository;
import org.binar.microservice.filmservice.filmservice.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;


    @Override
    public List<Seat> findAllSeat() {
        log.info("Showing seat from database");
        return seatRepository.findAll();
    }

}
