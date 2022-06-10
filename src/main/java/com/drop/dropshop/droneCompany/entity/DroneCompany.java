package com.drop.dropshop.droneCompany.entity;

import com.drop.dropshop.droneCompany.dto.DroneCompanyDto;
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
@Table(name = "drone_company")
@NoArgsConstructor
@AllArgsConstructor
public class DroneCompany extends Timestamped {
    @Id //기본키라는 것을 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(example = "자동 증가되는 db 내 id")
    private Long id;

    @ApiModelProperty(example = "드론 업체를 식별하는 uuid")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(nullable = false, unique = true, columnDefinition = "BINARY(16)")
    private UUID companyId;

    @Column(nullable = false, unique = true, length = 30)
    @ApiModelProperty(example = "드론 업체가 로그인 시 사용할 id")
    private String loginId;

    @ApiModelProperty(example = "드론 업체가 로그인 시 사용할 password")
    @Column(nullable = false, length = 20)
    private String loginPassword;

    @ApiModelProperty(example = "드론 업체명")
    @Column(nullable = false, unique = true, length = 30)
    private String companyName;

    @ApiModelProperty(example = "드론 업체 연락처")
    @Column(nullable = false, unique = true, length = 10)
    private String companyContract;

    @ApiModelProperty(example = "드론 업체 사업자 번호")
    @Column(nullable = false, unique = true, length = 10)
    private String buisenessNumber;

    public DroneCompany(DroneCompanyDto droneCompanyDto) {
        this.companyName = droneCompanyDto.getCompanyName();
        this.companyContract = droneCompanyDto.getCompanyContract();
        this.buisenessNumber = droneCompanyDto.getBuisenessNumber();
        this.loginPassword = droneCompanyDto.getCompanyContract();
    }
}