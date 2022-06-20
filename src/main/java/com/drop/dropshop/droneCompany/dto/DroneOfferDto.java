package com.drop.dropshop.droneCompany.dto;

import com.drop.dropshop.droneCompany.entity.CompanyDrone;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class DroneOfferDto {
    // 드론 모델 정보
    @ApiModelProperty(example = "드론 모델을 식별하는 uuid")
    private final UUID modelId;

    @ApiModelProperty(example = "드론 모델명")
    private final String modelName;

    @ApiModelProperty(example = "카메라 보유 여부")
    private final boolean camera;

    @ApiModelProperty(example = "회사 보유 드론 리스트")
    private final List<CompanyDrone> companyDroneList;
}
