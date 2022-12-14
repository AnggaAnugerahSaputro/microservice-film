package org.binar.microservice.filmservice.filmservice.service;

import org.binar.microservice.filmservice.filmservice.model.entity.Schedule;
import org.binar.microservice.filmservice.filmservice.model.request.ScheduleRequest;
import org.binar.microservice.filmservice.filmservice.model.response.ScheduleResponse;

import java.util.List;

public interface ScheduleService {

    ScheduleResponse addSchedule(ScheduleRequest scheduleRequest);
    List<ScheduleResponse> searchAllSchedule();
    ScheduleResponse updateSchedule(String scheduleId, ScheduleRequest scheduleRequest);
    Boolean deleteSchedule(String scheduleId);
    Schedule findById(String scheduleId);

}
