package com.drop.dropshop.droneCompany.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class DroneModelDto {
    @ApiModelProperty(example = "드론 모델명")
    private final String ModelName;
    @ApiModelProperty(example = "최대 적재 중량")
    private final double flightWeight;
    @ApiModelProperty(example = "최대 비행 시간")
    private final Long flightTime;
    @ApiModelProperty(example = "주행 가능 거리")
    private final double flightDistance;
    @ApiModelProperty(example = "야간 운행 허용 여부")
    private final boolean nightTimeFlight;
    @ApiModelProperty(example = "카메라 보유 여부")
    private final boolean camera;
    @ApiModelProperty(example = "작동 최저 온도")
    private final int lowestTemperature;
    @ApiModelProperty(example = "작동 최고 온도")
    private final int maximumTemperature;
}
