package com.drop.dropshop.deliveryAdmin.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {

    private final static int SUCCESS = 200;
    private final static int DOUBLE_LOGIN_CODE = 201;
    private final static int INVALID_REFRESH_TOKEN_CODE = 202;
    private final static int NOT_EXPIRED_TOKEN_YET_CODE = 203;
    private final static int NOT_FOUND = 400;
    private final static int FAILED = 500;

    private final static String SUCCESS_MESSAGE = "SUCCESS";
    private final static String NOT_FOUND_MESSAGE = "NOT FOUND";
    private final static String FAILED_MESSAGE = "Server error detected.";
    private final static String INVALID_ACCESS_TOKEN_MESSAGE = "Invalid access token.";
    private final static String INVALID_REFRESH_TOKEN_MESSAGE = "Invalid refresh token.";
    private final static String NOT_EXPIRED_TOKEN_YET_MESSAGE = "Not expired token yet.";

    private final ApiResponseHeader header;
    private final Map<String, T> body;

    public static <T> ApiResponse<T> success(String name, T body) {
        Map<String, T> map = new HashMap<>();
        map.put(name, body);

        return new ApiResponse<T>(new ApiResponseHeader(SUCCESS, SUCCESS_MESSAGE), map);
    }

    public static <T> ApiResponse<T> fail() {
        return new ApiResponse<T>(new ApiResponseHeader(FAILED, FAILED_MESSAGE), null);
    }

    public static <T> ApiResponse<T> invalidAccessToken() {
        return new ApiResponse<T>(new ApiResponseHeader(FAILED, INVALID_ACCESS_TOKEN_MESSAGE), null);
    }

    public static <T> ApiResponse<T> invalidRefreshToken() {
        return new ApiResponse<T>(new ApiResponseHeader(INVALID_REFRESH_TOKEN_CODE, INVALID_REFRESH_TOKEN_MESSAGE), null);
    }

    public static <T> ApiResponse<T> notExpiredTokenYet() {
        return new ApiResponse<T>(new ApiResponseHeader(NOT_EXPIRED_TOKEN_YET_CODE, NOT_EXPIRED_TOKEN_YET_MESSAGE), null);
    }
}