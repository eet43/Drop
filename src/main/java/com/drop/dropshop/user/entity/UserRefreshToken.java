package com.drop.dropshop.user.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_refresh_token")
@NoArgsConstructor
@AllArgsConstructor
public class UserRefreshToken extends Timestamped {

    @Id //기본키라는 것을 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String accessToken;

    @Column(nullable = false, unique = true)
    private String refreshToken;

    @Column(nullable = false, unique = true)
    private String refreshTokenExpirationAt;

    public UserRefreshToken(String username, String accessToken, String refreshToken, String refreshTokenExpirationAt) {
        this.username = username;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.refreshTokenExpirationAt = refreshTokenExpirationAt;
    }
}
