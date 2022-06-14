package com.drop.dropshop.droneCompany.entity;

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
@Table(name = "company_own_drone")
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDroneDetail extends Timestamped {
    @Id //기본키라는 것을 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(example = "자동 증가되는 db 내 id")
    private Long id;

    @ApiModelProperty(example = "각 드론 재고 하나를 식별하는 uuid")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(nullable = false, unique = true)
    private UUID droneId;

    @ApiModelProperty(example = "업체의 보유 드론 목록을 식별하는 uuid")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(nullable = false, columnDefinition = "BINARY(16)")
    private UUID companyDroneId;

    @ApiModelProperty(example = "드론의 시리얼 번호")
    @Column(nullable = false, unique = true)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(example = "드론 상태 코드")
    private StatusCode statusCode;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(example = "드론 운행 상태 코드")
    private RaceCode raceCode;

    public CompanyDroneDetail(UUID companyDroneId, String serialNumber, StatusCode statusCode, RaceCode raceCode) {
        this.companyDroneId = companyDroneId;
        this.serialNumber = serialNumber;
        this.statusCode = statusCode;
        this.raceCode = raceCode;
    }
}
