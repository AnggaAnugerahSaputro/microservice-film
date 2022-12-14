package org.binar.microservice.filmservice.filmservice.service;

import org.binar.microservice.filmservice.filmservice.model.entity.Studio;
import org.binar.microservice.filmservice.filmservice.model.request.FilmRequest;
import org.binar.microservice.filmservice.filmservice.model.request.StudioRequest;
import org.binar.microservice.filmservice.filmservice.model.response.FilmResponse;
import org.binar.microservice.filmservice.filmservice.model.response.StudioResponse;

import java.util.List;

public interface StudioService {

    StudioResponse addStudio(StudioRequest studioRequest);
    List<StudioResponse> searchAllStudio();
    StudioResponse updateStudio(Integer studioId, StudioRequest  studioRequest);
    Boolean deleteStudio(String studioName);
    StudioResponse findByStudioName(String studioName);

}
