package com.drop.dropshop.droneCompany.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    BUSINESS_NUMBER_NOT_VALID(400, "BUSINESS NUMBER NOT VALID"),
    INTER_SERVER_ERROR(500, "INTER SERVER ERROR"),
    RESOURCE_NOT_FOUND(404, "RESOURCE NOT FOUND");

    private int status;
    private String message;
}
