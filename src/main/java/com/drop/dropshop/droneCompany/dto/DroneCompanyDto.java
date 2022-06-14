package com.drop.dropshop.droneCompany.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class DroneCompanyDto {
    @ApiModelProperty(example = "드론 업체명")
    private final String companyName;
    @ApiModelProperty(example = "드론 업체 연락처")
    private final String companyContract;
    @ApiModelProperty(example = "드론 업체 사업자 번호")
    private final String buisenessNumber;
}
