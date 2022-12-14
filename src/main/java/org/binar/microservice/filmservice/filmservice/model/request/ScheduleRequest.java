package org.binar.microservice.filmservice.filmservice.model.request;

import lombok.Data;
import org.binar.microservice.filmservice.filmservice.model.entity.Film;
import org.binar.microservice.filmservice.filmservice.model.entity.Schedule;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScheduleRequest {

    @NotEmpty(message = "Schedule ID is required.")
    private String  scheduleId;

    @NotEmpty(message = "Show Date is required.")
    private LocalDate showDate;

    @NotEmpty(message = "Start Time is required.")
    private LocalTime startTime;

    @NotEmpty(message = "End Time is required.")
    private LocalTime endTime;

    @NotEmpty(message = "Price is required.")
    private BigDecimal price;

    @NotEmpty(message = "Film name is required.")
    private String filmName;

    public Schedule toSchedules(Film film) {
                Schedule schedule =  new Schedule();
                schedule.setScheduleId(this.scheduleId);
                schedule.setShowDate(this.showDate);
                schedule.setStartTime(this.startTime);
                schedule.setEndTime(this.endTime);
                schedule.setPrice(this.price);
                schedule.setFilm(film);
                return schedule;

    }
}
