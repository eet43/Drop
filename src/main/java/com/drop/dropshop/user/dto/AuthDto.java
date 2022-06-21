package com.drop.dropshop.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AuthDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor

    public static class LoginDTO {

        private String username;
        private String password;
    }

    /**
     * Refresh Token을 사용하여 새로운 Access Token을 발급받을 때 사용하는 DTO
     */
    @Getter
    @Setter
    public static class GetNewAccessTokenDTO {


        private long refreshIdx;
    }
}

