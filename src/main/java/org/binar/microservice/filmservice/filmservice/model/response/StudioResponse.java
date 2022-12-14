package org.binar.microservice.filmservice.filmservice.model.response;

import lombok.Builder;
import lombok.Data;
import org.binar.microservice.filmservice.filmservice.model.entity.Studio;

@Data
@Builder
public class StudioResponse {

    private Integer studioId;
    private String studioName;

    public static StudioResponse build(Studio studio) {
        return StudioResponse.builder()
                .studioId(studio.getStudioId())
                .studioName(studio.getStudioName())
                .build();
    }
}
