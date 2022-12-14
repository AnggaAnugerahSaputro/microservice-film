package org.binar.microservice.filmservice.filmservice.controller;


import org.binar.microservice.filmservice.filmservice.model.request.FilmRequest;
import org.binar.microservice.filmservice.filmservice.model.response.FilmResponse;
import org.binar.microservice.filmservice.filmservice.model.response.ResponseMessage;
import org.binar.microservice.filmservice.filmservice.service.FilmService;
import org.binar.microservice.filmservice.filmservice.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/films")
public class FilmController {


    private FilmService filmService;

    private SeatService seatService;

    @Autowired
    public FilmController(FilmService filmService, SeatService seatService) {
        this.filmService = filmService;
        this.seatService = seatService;
    }

    @PostMapping(value = "/add-film", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseMessage> createFilm(@RequestBody FilmRequest filmRequest) {
        ResponseMessage message = new ResponseMessage();
        FilmResponse filmResponse = filmService.addFilm(filmRequest);
        if(filmResponse == null) {
            message.setStatus(HttpStatus.BAD_REQUEST.value());
            message.setMessage("Failed to create Film");
            return ResponseEntity.badRequest().body(message);
        }
        else {
            message.setStatus(HttpStatus.CREATED.value());
            message.setMessage("Create new Film");
            message.setData(filmResponse);
            return ResponseEntity.ok().body(message);

        }
    }

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseMessage> getAllFilms() {
        ResponseMessage message = new ResponseMessage();
        try {
            List<FilmResponse> getAllFilms = filmService.searchAllFilm();
            message.setMessage("Success get all Films");
            message.setStatus(HttpStatus.OK.value());
            message.setData(getAllFilms);
            return ResponseEntity.ok().body(message);
        }catch (Exception exception)
        {
            message.setMessage("Failed get all Films");
            message.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(message);
        }
    }

    @PutMapping(value = "/update/{filmCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> updateFilms(@PathVariable String filmCode, @RequestBody FilmRequest filmRequest)
    {
        ResponseMessage message = new ResponseMessage();
        FilmResponse filmResponse = filmService.updateFilm(filmCode, filmRequest);
        if(filmResponse == null)
        {
            message.setStatus(HttpStatus.CONFLICT.value());
            message.setMessage("Failed to update Films");
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(message);
        }
        else
        {
            message.setStatus(HttpStatus.OK.value());
            message.setMessage("Update Films success");
            message.setData(filmResponse);
            return ResponseEntity.ok().body(message);
        }
    }

    @DeleteMapping(value = "/delete/{filmName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> deleteByFilmName(@PathVariable String filmName)
    {
        ResponseMessage message = new ResponseMessage();
        Boolean deleteFilm = filmService.deleteFilm(filmName);
        if(deleteFilm)
        {
            message.setMessage("Success delete film");
            message.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok().body(message);
        }
        else
        {
            message.setMessage("Failed delete Film is not found");
            message.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(message);
        }
    }

    @GetMapping(value = "/find/{filmName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> getFilmByName(@PathVariable String filmName){
        ResponseMessage message = new ResponseMessage();
        try {
            FilmResponse getFilm = filmService.findByName(filmName);
            message.setMessage("Success get Film By name");
            message.setStatus(HttpStatus.OK.value());
            message.setData(getFilm);
            return ResponseEntity.ok().body(message);
        }catch (Exception exception)
        {
            message.setMessage("Failed get Film By name");
            message.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(message);
        }
    }


    @ResponseBody
    @PutMapping("/update-status/{filmCode}")

    public ResponseEntity<ResponseMessage> updateReadStatus(@PathVariable("filmCode") String filmCode) {
        ResponseMessage message = new ResponseMessage();
        FilmResponse filmResponse = filmService.updateIsRead(filmCode);
        if (filmResponse == null) {
            message.setMessage("Failed notification user");
            message.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(message);
        } else {
            message.setStatus(HttpStatus.OK.value());
            message.setMessage("Success update read user notification");
            message.setData(filmResponse);
            return ResponseEntity.ok().body(message);
        }
    }

    @GetMapping(value = "/findNow", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> getFilmNow(){
        ResponseMessage message = new ResponseMessage();
        try {
            List<FilmResponse> getFilm = filmService.getFilmNow();
            message.setMessage("Success get Film By name");
            message.setStatus(HttpStatus.OK.value());
            message.setData(getFilm);
            return ResponseEntity.ok().body(message);
        }catch (Exception exception)
        {
            message.setMessage("Failed get Film By name");
            message.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(message);
        }
    }
}