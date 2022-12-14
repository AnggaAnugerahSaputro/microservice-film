package org.binar.microservice.filmservice.filmservice.repository;

import org.binar.microservice.filmservice.filmservice.model.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ScheduleRepository extends JpaRepository<Schedule, String>{
}
