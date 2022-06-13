package com.drop.dropshop.droneCompany.entity;

import com.drop.dropshop.droneCompany.dto.DroneModelDto;
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
@Table(name = "drone_model")
@NoArgsConstructor
@AllArgsConstructor
public class DroneModel extends Timestamped {
    @Id //기본키라는 것을 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(example = "자동 증가되는 db 내 id")
    private Long id;

    @ApiModelProperty(example = "드론 모델을 식별하는 uuid")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(nullable = false, unique = true, columnDefinition = "BINARY(16)")
    private UUID modelId;

    @Column(nullable = false, unique = true, length = 100)
    @ApiModelProperty(example = "드론 모델명")
    private String modelName;

    @Column(nullable = false)
    @ApiModelProperty(example = "최대 적재 중량")
    private double flightWeight;

    @Column(nullable = false)
    @ApiModelProperty(example = "최대 비행 시간")
    private Long flightTime;

    @Column(nullable = false)
    @ApiModelProperty(example = "주행 가능 거리")
    private double flightDistance;

    @Column(nullable = false)
    @ApiModelProperty(example = "야간 운행 허용 여부")
    private boolean nightTimeFlight;

    @Column(nullable = false)
    @ApiModelProperty(example = "카메라 보유 여부")
    private boolean camera;

    @Column(nullable = false)
    @ApiModelProperty(example = "작동 최저 온도")
    private int lowestTemperature;

    @Column(nullable = false)
    @ApiModelProperty(example = "작동 최고 온도")
    private int maximumTemperature;

    public DroneModel(DroneModelDto requestDto) {
        this.modelName = requestDto.getModelName();
        this.flightWeight = requestDto.getFlightWeight();;
        this.flightTime = requestDto.getFlightTime();
        this.flightDistance = requestDto.getFlightDistance();
        this.nightTimeFlight = requestDto.isNightTimeFlight();
        this.camera = requestDto.isCamera();
        this.lowestTemperature = requestDto.getLowestTemperature();
        this.maximumTemperature = requestDto.getMaximumTemperature();
    }
}
