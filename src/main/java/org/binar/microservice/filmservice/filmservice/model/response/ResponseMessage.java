package org.binar.microservice.filmservice.filmservice.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage {
    private Integer status;
    private String message;
    private Object data;
}
