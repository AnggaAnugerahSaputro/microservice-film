package org.binar.microservice.filmservice.filmservice.service.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.binar.microservice.filmservice.filmservice.model.entity.Film;
import org.binar.microservice.filmservice.filmservice.model.entity.Schedule;
import org.binar.microservice.filmservice.filmservice.model.request.ScheduleRequest;
import org.binar.microservice.filmservice.filmservice.model.response.ScheduleResponse;
import org.binar.microservice.filmservice.filmservice.repository.FilmRepository;
import org.binar.microservice.filmservice.filmservice.repository.ScheduleRepository;
import org.binar.microservice.filmservice.filmservice.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public ScheduleResponse addSchedule(ScheduleRequest scheduleRequest) {
        Schedule schedule = new Schedule();
       Film filmModel = filmRepository.findByFilmName(scheduleRequest.getFilmName());
        try {
            schedule.setFilm(filmModel);
            schedule.setScheduleId(scheduleRequest.getScheduleId());
            schedule.setShowDate(scheduleRequest.getShowDate());
            schedule.setStartTime(scheduleRequest.getStartTime());
            schedule.setEndTime(scheduleRequest.getEndTime());
            schedule.setPrice(scheduleRequest.getPrice());
            scheduleRepository.save(schedule);
            return ScheduleResponse.build(schedule);
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public List<ScheduleResponse> searchAllSchedule() {
        List<Schedule> allSchedule = scheduleRepository.findAll();
        List<ScheduleResponse> allScheduleResponse = new ArrayList<>();
        for (Schedule schedule : allSchedule) {
            ScheduleResponse scheduleResponse = ScheduleResponse.build(schedule);
            allScheduleResponse.add(scheduleResponse);
        }
        return allScheduleResponse;
    }

    @Override
    public ScheduleResponse updateSchedule(String scheduleId, ScheduleRequest scheduleRequest) {
        Optional<Schedule> isSchedule = scheduleRepository.findById(scheduleId);
        if (isSchedule.isPresent()) {
            Schedule schedule = isSchedule.get();
            schedule.setShowDate(scheduleRequest.getShowDate());
            schedule.setStartTime(scheduleRequest.getStartTime());
            schedule.setEndTime(scheduleRequest.getEndTime());
            schedule.setPrice(scheduleRequest.getPrice());
            ;
            try {
                scheduleRepository.save(schedule);
                return ScheduleResponse.build(schedule);
            } catch (Exception exception) {
                return null;
            }
        } else {
            throw new RuntimeException("Countries is update");
        }
    }

    @Override
    public Boolean deleteSchedule(String scheduleId) {
        Optional<Schedule> schedule = scheduleRepository.findById(scheduleId);
        if(schedule.isPresent()) {
            scheduleRepository.deleteById(scheduleId);
            return true;
        }
        else{
            return false;
        }
    }


    @Override
   public Schedule findById(String scheduleId) {
        Optional<Schedule> result = scheduleRepository.findById(scheduleId);
        if (result.isPresent()) {  // jika misal ada
            log.info("Data Schedule available");
            return result.get();
        }else {
            log.error("Schedule data not entered");
        }
        return null;
    }
}
