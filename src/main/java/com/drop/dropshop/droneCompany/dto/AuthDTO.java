package com.drop.dropshop.droneCompany.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class AuthDTO {

    /**
     * 로그인 시 사용하는 DTO
     */
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginDTO {
        @NotBlank
        @ApiModelProperty(example = "드론 업체가 로그인 시 사용할 id", required = true)
        private String companyId;

        @NotBlank
        @ApiModelProperty(example = "드론 업체가 로그인 시 사용할 password", required = true)
        private String companyPassword;
    }

    /**
     * Refresh Token을 사용하여 새로운 Access Token을 발급받을 때 사용하는 DTO
     */
    @Getter
    @Setter
    public static class GetNewAccessTokenDTO {

        @ApiModelProperty(example = "refresh token 인덱스", required = true)
        private long refreshIdx;
    }
}