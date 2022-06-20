package com.drop.dropshop.droneCompany.entity;

import com.drop.dropshop.droneCompany.dto.OwnDroneEnrollDto;
import com.drop.dropshop.droneCompany.util.Timestamped;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "company_drone")
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDrone extends Timestamped {
    @Id //기본키라는 것을 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(example = "자동 증가되는 db 내 id")
    private Long id;

    @ApiModelProperty(example = "업체의 보유 드론 목록을 식별하는 uuid")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(nullable = false, unique = true, columnDefinition = "BINARY(16)")
    private UUID companyDroneId;

    @ApiModelProperty(example = "드론 업체를 식별하는 uuid")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(nullable = false, columnDefinition = "BINARY(16)")
    private UUID companyId;

    @ApiModelProperty(example = "드론 모델을 식별하는 uuid")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(nullable = false, columnDefinition = "BINARY(16)")
    private UUID modelId;

    @Column(nullable = false)
    @ApiModelProperty(example = "드론 배송 기본 가격")
    private int basePrice;

    @Column(nullable = false)
    @ApiModelProperty(example = "동일 모델 보유 드론 대수")
    private int num;

    @Column(nullable = false)
    @ApiModelProperty(example = "동일 모델 운행 가능 드론 대수")
    private int operableNum;

    public CompanyDrone(UUID companyId, UUID modelId, int basePrice, int num, int operableNum) {
        this.companyId = companyId;
        this.modelId = modelId;
        this.basePrice = basePrice;
        this.num = num;
        this.operableNum = operableNum;
    }

    public void update(OwnDroneEnrollDto requestDto) {
        this.basePrice = requestDto.getBasePrice();
        this.num = requestDto.getNum();
        this.operableNum = requestDto.getOperableNum();
    }
}
