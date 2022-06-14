package com.drop.dropshop.droneCompany.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AccessDeniedException extends Exception {
    private ErrorCode errorCode;
    private String detail;

    public AccessDeniedException(String message, ErrorCode errorCode, String detail) {
        super(message);
        this.errorCode = errorCode;
        this.detail = detail;
    }
}
