package com.drop.dropshop.droneCompany.dto;

import com.drop.dropshop.droneCompany.entity.RaceCode;
import com.drop.dropshop.droneCompany.entity.StatusCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@RequiredArgsConstructor
public class DroneDetailDto {
    @ApiModelProperty(example = "드론의 시리얼 번호")
    private final String serialNumber;
    @ApiModelProperty(example = "드론 상태 코드")
    private final StatusCode statusCode;
    @ApiModelProperty(example = "드론 운행 상태 코드")
    private final RaceCode raceCode;
}
