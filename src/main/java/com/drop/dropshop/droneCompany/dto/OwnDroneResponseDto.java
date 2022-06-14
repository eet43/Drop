package com.drop.dropshop.droneCompany.dto;

import com.drop.dropshop.droneCompany.entity.CompanyDroneDetail;
import com.drop.dropshop.droneCompany.entity.DroneCompany;
import com.drop.dropshop.droneCompany.entity.DroneModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class OwnDroneResponseDto {
    @ApiModelProperty(example = "드론 회사")
    private final DroneCompany droneCompany;
    @ApiModelProperty(example = "드론 모델")
    private final DroneModel droneModel;
    @ApiModelProperty(example = "동일 모델 보유 드론 대수")
    private final int num;
    @ApiModelProperty(example = "동일 모델 운행 가능 드론 대수")
    private final int operableNum;
    @ApiModelProperty(example = "상세 드론 리스트")
    private final List<CompanyDroneDetail> companyDroneDetailList;
}
