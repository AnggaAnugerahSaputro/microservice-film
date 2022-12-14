package org.binar.microservice.filmservice.filmservice.model.request;

import lombok.Data;
import org.binar.microservice.filmservice.filmservice.model.entity.Studio;

@Data
public class StudioRequest {

    private Integer studioId;
    private String studioName;

    public Studio toStudio() {
        return Studio.builder()
                .studioId(this.studioId)
                .studioName(this.studioName)
                .build();
    }
}
