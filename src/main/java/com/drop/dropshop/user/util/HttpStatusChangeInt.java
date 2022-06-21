package com.drop.dropshop.user.util;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class HttpStatusChangeInt {
    public static int ChangeStatusCode(String statusText){
        HttpStatus httpStatus = Arrays.stream(HttpStatus.values())
                .filter(status -> status.name().equals(statusText))
                .findAny()
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
        return httpStatus.value();
    }
}
