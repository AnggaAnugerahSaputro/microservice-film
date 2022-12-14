package org.binar.microservice.filmservice.filmservice.controller;

import org.binar.microservice.filmservice.filmservice.model.entity.Schedule;
import org.binar.microservice.filmservice.filmservice.model.request.ScheduleRequest;
import org.binar.microservice.filmservice.filmservice.model.response.ResponseMessage;
import org.binar.microservice.filmservice.filmservice.model.response.ScheduleResponse;
import org.binar.microservice.filmservice.filmservice.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/schedule")
public class ScheduleController {


    private ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping(value = "/add-schedule", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseMessage> createCountries(@RequestBody ScheduleRequest scheduleRequest) {
        ResponseMessage message = new ResponseMessage();
        ScheduleResponse scheduleResponse = scheduleService.addSchedule(scheduleRequest);
        if(scheduleResponse == null) {
            message.setStatus(HttpStatus.BAD_REQUEST.value());
            message.setMessage("Failed to create Schedule");
            return ResponseEntity.badRequest().body(message);
        }
        else {
            message.setStatus(HttpStatus.CREATED.value());
            message.setMessage("Create new Schedule");
            message.setData(scheduleResponse);
            return ResponseEntity.ok().body(message);

        }
    }

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseMessage> getAllSchedules() {
        ResponseMessage message = new ResponseMessage();
        try {
            List<ScheduleResponse> getAllSchedule = scheduleService.searchAllSchedule();
            message.setMessage("Success get all Schedules");
            message.setStatus(HttpStatus.OK.value());
            message.setData(getAllSchedule);
            return ResponseEntity.ok().body(message);
        }catch (Exception exception)
        {
            message.setMessage("Failed get all Schedules");
            message.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(message);
        }
    }

    @PutMapping(value = "/update/{scheduleId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseMessage> updateSchedules(@PathVariable String scheduleId, @RequestBody ScheduleRequest scheduleRequest)
    {
        ResponseMessage message = new ResponseMessage();
        ScheduleResponse scheduleResponse = scheduleService.updateSchedule(scheduleId, scheduleRequest);
        if(scheduleResponse == null)
        {
            message.setStatus(HttpStatus.CONFLICT.value());
            message.setMessage("Failed to update Schedule");
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(message);
        }
        else
        {
            message.setStatus(HttpStatus.OK.value());
            message.setMessage("Update schedule success");
            message.setData(scheduleResponse);
            return ResponseEntity.ok().body(message);
        }
    }

    @DeleteMapping(value = "/delete/{scheduleId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseMessage> deleteByScheduleId(@PathVariable String scheduleId)
    {
        ResponseMessage message = new ResponseMessage();
        Boolean deleteSchedules = scheduleService.deleteSchedule(scheduleId);
        if(deleteSchedules)
        {
            message.setMessage("Success delete Schedule");
            message.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok().body(message);
        }
        else
        {
            message.setMessage("Failed delete Schedule is not found");
            message.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(message);
        }
    }

    @GetMapping(value = "/find/{scheduleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> getScheduleByDate(@PathVariable String scheduleId){
        ResponseMessage message = new ResponseMessage();
        try {
            Schedule getSchedules = scheduleService.findById(scheduleId);
            message.setMessage("Success get Schedule By Date");
            message.setStatus(HttpStatus.OK.value());
            message.setData(getSchedules);
            return ResponseEntity.ok().body(message);
        }catch (Exception exception)
        {
            message.setMessage("Failed get Schedule By Date");
            message.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(message);
        }
    }
}
