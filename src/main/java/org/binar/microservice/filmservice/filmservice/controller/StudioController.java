package org.binar.microservice.filmservice.filmservice.controller;


import org.binar.microservice.filmservice.filmservice.model.request.StudioRequest;
import org.binar.microservice.filmservice.filmservice.model.response.ResponseMessage;
import org.binar.microservice.filmservice.filmservice.model.response.StudioResponse;
import org.binar.microservice.filmservice.filmservice.service.SeatService;
import org.binar.microservice.filmservice.filmservice.service.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/studios")
public class StudioController {

    private StudioService studioService;

    private SeatService seatService;

    @Autowired
    public StudioController(StudioService studioService, SeatService seatService) {
        this.studioService = studioService;
        this.seatService = seatService;
    }

    @PostMapping(value = "/add-Studio", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseMessage> createStudio(@RequestBody StudioRequest studioRequest) {
        ResponseMessage message = new ResponseMessage();
        StudioResponse studioResponse = studioService.addStudio(studioRequest);
        if(studioResponse == null) {
            message.setStatus(HttpStatus.BAD_REQUEST.value());
            message.setMessage("Failed to create Studio");
            return ResponseEntity.badRequest().body(message);
        }
        else {
            message.setStatus(HttpStatus.CREATED.value());
            message.setMessage("Create new Studio");
            message.setData(studioResponse);
            return ResponseEntity.ok().body(message);

        }
    }

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseMessage> getAllStudios() {
        ResponseMessage message = new ResponseMessage();
        try {
            List<StudioResponse> getAllStudios = studioService.searchAllStudio();
            message.setMessage("Success get all Studios");
            message.setStatus(HttpStatus.OK.value());
            message.setData(getAllStudios);
            return ResponseEntity.ok().body(message);
        }catch (Exception exception)
        {
            message.setMessage("Failed get all Studios");
            message.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(message);
        }
    }

    @PutMapping(value = "/update/{studioId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> updateStudio(@PathVariable Integer studioId, @RequestBody StudioRequest studioRequest)
    {
        ResponseMessage message = new ResponseMessage();
        StudioResponse studioResponse = studioService.updateStudio(studioId, studioRequest);
        if(studioResponse == null)
        {
            message.setStatus(HttpStatus.CONFLICT.value());
            message.setMessage("Failed to update Studio");
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(message);
        }
        else
        {
            message.setStatus(HttpStatus.OK.value());
            message.setMessage("Update Studio success");
            message.setData(studioResponse);
            return ResponseEntity.ok().body(message);
        }
    }

    @DeleteMapping(value = "/delete/{studioName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> deleteByStudioName(@PathVariable String studioName)
    {
        ResponseMessage message = new ResponseMessage();
        Boolean deleteStudio = studioService.deleteStudio(studioName);
        if(deleteStudio)
        {
            message.setMessage("Success delete Studio");
            message.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok().body(message);
        }
        else
        {
            message.setMessage("Failed delete Studio is not found");
            message.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(message);
        }
    }

    @GetMapping(value = "/find/{studioName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> getStudioByName(@PathVariable String studioName){
        ResponseMessage message = new ResponseMessage();
        try {
            StudioResponse getStudio = studioService.findByStudioName(studioName);
            message.setMessage("Success get Studio By name");
            message.setStatus(HttpStatus.OK.value());
            message.setData(getStudio);
            return ResponseEntity.ok().body(message);
        }catch (Exception exception)
        {
            message.setMessage("Failed get Studio By name");
            message.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(message);
        }
    }

    @GetMapping(value = "/get-allSeat")
    public ResponseEntity<ResponseMessage> getAllSeat() {
        ResponseMessage message = new ResponseMessage();
        try {
            message.setMessage("Success get all Studios");
            message.setStatus(HttpStatus.OK.value());
            message.setData(seatService.findAllSeat());
            return ResponseEntity.ok().body(message);
        }catch (Exception exception)
        {
            message.setMessage("Failed get all Studios");
            message.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(message);
        }
    }
}
