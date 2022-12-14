package org.binar.microservice.filmservice.filmservice.service.implement;

import org.binar.microservice.filmservice.filmservice.model.entity.Film;
import org.binar.microservice.filmservice.filmservice.model.entity.Studio;
import org.binar.microservice.filmservice.filmservice.model.request.StudioRequest;
import org.binar.microservice.filmservice.filmservice.model.response.FilmResponse;
import org.binar.microservice.filmservice.filmservice.model.response.StudioResponse;
import org.binar.microservice.filmservice.filmservice.repository.StudioRepository;
import org.binar.microservice.filmservice.filmservice.service.StudioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudioServiceImpl implements StudioService {


    private static final Logger logger = LoggerFactory.getLogger(StudioServiceImpl.class);

    private StudioRepository studioRepository;

    @Autowired
    public StudioServiceImpl(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

    @Override
    public StudioResponse addStudio(StudioRequest studioRequest) {
        Studio studio = studioRequest.toStudio();
        try {
            logger.info("add Studio Success");
            studioRepository.save(studio);
            return StudioResponse.build(studio);
        } catch (Exception exception) {
            logger.error("studio not found");
            return null;
        }
    }

    @Override
    public List<StudioResponse> searchAllStudio() {
        List<Studio> allStudios = studioRepository.findAll();
        List<StudioResponse> allStudiosResponse = new ArrayList<>();
        for (Studio studio : allStudios) {
            StudioResponse studioResponse = StudioResponse.build(studio);
            allStudiosResponse.add(studioResponse);
        }
        return allStudiosResponse;
    }

    @Override
    public StudioResponse updateStudio(Integer studioId, StudioRequest studioRequest) {
        Optional<Studio> isStudio = studioRepository.findById(studioId);
        if (isStudio.isPresent()) {
            Studio studio = isStudio.get();
            studio.setStudioName(studioRequest.getStudioName());
            try {
                logger.info("save studio success");
                studioRepository.save(studio);
                return StudioResponse.build(studio);
            } catch (Exception exception) {
                return null;
            }
        } else {
            throw new RuntimeException("Studio is update");
        }
    }

    @Override
    public Boolean deleteStudio(String studioName) {
        Studio studio = studioRepository.findByStudioName(studioName);
        if(studio != null) {
            logger.info("delete studio success");
            studioRepository.deleteById(studio.getStudioId());
            return true;
        }
        else
            return false;
    }

    @Override
    public StudioResponse findByStudioName(String studioName) {
        logger.info("search studio by name {}", studioName);
        Studio studio = studioRepository.findByStudioName(studioName);
        if (studio != null){
            return StudioResponse.build(studio);
        }else {
            return null;
        }
    }
}
