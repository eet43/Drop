package com.drop.dropshop.droneCompany.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class OpenApiErrorResponse {
    private Date timestamp;
    private int status;
    private String message;
    private Map details;

    public OpenApiErrorResponse(ErrorCode errorCode, Map jsonObject){
        this.timestamp = new Date();
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.details = jsonObject;
    }
}
