package com.drop.dropshop.droneCompany.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OpenApiResponseErrorException extends Exception {
    private ErrorCode errorCode;
    private String detail;
    private Object apiErrorCode;
    private Object apiErrorMessage;

    public OpenApiResponseErrorException(String message, ErrorCode errorCode, String detail, Object apiErrorCode, Object apiErrorMessage) {
        super(message);
        this.errorCode = errorCode;
        this.detail = detail;
        this.apiErrorCode = apiErrorCode;
        this.apiErrorMessage = apiErrorMessage;
    }
}
