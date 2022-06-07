package com.drop.dropshop.droneCompany.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusinessNumberNotValidException extends Exception {
    private ErrorCode errorCode;
    private String detail;

    public BusinessNumberNotValidException(String message, ErrorCode errorCode, String detail) {
        super(message);
        this.errorCode = errorCode;
        this.detail = detail;
    }
}
