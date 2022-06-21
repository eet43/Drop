package com.drop.dropshop.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ResponseDetails {
    private Date timestamp;
    private Object data;
    private int httpStatus;
    private String path;
}