package org.binar.microservice.filmservice.filmservice.model.response;

import lombok.Builder;
import lombok.Data;
import org.binar.microservice.filmservice.filmservice.model.entity.Schedule;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class ScheduleResponse {


    private String  scheduleId;
    private LocalDate showDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private BigDecimal price;
    private String filmName;

    public static ScheduleResponse build(Schedule schedules) {
        return ScheduleResponse.builder()
                .scheduleId(schedules.getScheduleId())
                .showDate(schedules.getShowDate())
                .startTime(schedules.getStartTime())
                .endTime(schedules.getEndTime())
                .price(schedules.getPrice())
                .filmName(schedules.getFilm().getFilmName())
                .build();
    }
}
