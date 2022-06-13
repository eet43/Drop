package com.drop.dropshop.droneCompany.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TokenDto {
    @ApiModelProperty(example = "accessToken")
    private final Object accessToken;
    @ApiModelProperty(example = "refreshIdx")
    private final Object refreshIdx;
}
