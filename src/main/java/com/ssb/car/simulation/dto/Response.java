package com.ssb.car.simulation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@NoArgsConstructor
@Getter
@JsonInclude(Include.NON_NULL)
public class Response {

    private int statusCode;

    private Object data;

    private String contextPath;

    private String exceptionMessage;

    private Date timeStamp;

    public Response(int statusCode, Object data) {
        super();
        this.statusCode = statusCode;
        this.data = data;
    }

    public Response(int statusCode, String contextPath, String exceptionMessage, Date timeStamp) {
        super();
        this.statusCode = statusCode;
        this.contextPath = contextPath;
        this.exceptionMessage = exceptionMessage;
        this.timeStamp = timeStamp;
    }

}
