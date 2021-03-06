package com.drop.dropshop.droneCompany.exception;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessNumberNotValidException.class)
    public ResponseEntity<ErrorResponse> businessNumberNotValidException(BusinessNumberNotValidException ex) {
        log.error("BusinessNumberNotValidException", ex);
        ErrorResponse response = new ErrorResponse(ex.getErrorCode(), ex.getDetail());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceException.class)
    public ResponseEntity<ErrorResponse> noResourceException(NoResourceException ex) {
        log.error("NoResourceException", ex);
        ErrorResponse response = new ErrorResponse(ex.getErrorCode(), ex.getDetail());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> accessDeniedException(AccessDeniedException ex) {
        log.error("AccessDeniedException", ex);
        ErrorResponse response = new ErrorResponse(ex.getErrorCode(), ex.getDetail());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(OpenApiResponseErrorException.class)
    public ResponseEntity<OpenApiErrorResponse> openApiResponseErrorException(OpenApiResponseErrorException ex) {
        log.error("OpenApiResponseErrorException", ex);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("status", ex.getApiErrorCode().toString());
        map.put("errorMessage", ex.getApiErrorMessage().toString());
        OpenApiErrorResponse response = new OpenApiErrorResponse(ex.getErrorCode(), map);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error("handleException", ex);
        ErrorResponse response = new ErrorResponse(ErrorCode.INTER_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
