package com.drop.dropshop.droneCompany.entity;

import com.drop.dropshop.droneCompany.util.Timestamped;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "refresh_token")
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken extends Timestamped  {
    @Id //기본키라는 것을 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(example = "자동 증가되는 db 내 id")
    private long idx;

    @Column(nullable = false, unique = true)
    @ApiModelProperty(example = "드론 업체 로그인 아이디")
    private String companyId;

    @Column(nullable = false, unique = true)
    @ApiModelProperty(example = "access token")
    private String accessToken;

    @Column(nullable = false, unique = true)
    @ApiModelProperty(example = "refresh token")
    private String refreshToken;

    @Column(nullable = false, unique = true)
    @ApiModelProperty(example = "refresh token 유효 시간")
    private String refreshTokenExpirationAt;

    public RefreshToken(String companyId, String accessToken, String refreshToken, String refreshTokenExpirationAt) {
        this.companyId = companyId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.refreshTokenExpirationAt = refreshTokenExpirationAt;
    }
}
