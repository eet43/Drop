package com.drop.dropshop.droneCompany.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ResponseDetails {
    private Date timestamp;
    private Object data;
    private int httpStatus;
    private String path;
}
