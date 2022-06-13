package com.drop.dropshop.droneCompany.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UUIDDto {
    @ApiModelProperty(example = "uuid")
    private final String uuid;
}
