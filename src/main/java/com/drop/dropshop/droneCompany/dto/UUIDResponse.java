package com.drop.dropshop.droneCompany.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class UUIDResponse {
    @ApiModelProperty(example = "uuid")
    private final String uuid;
}
