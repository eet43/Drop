package com.drop.dropshop.user.repository;


import com.drop.dropshop.user.entity.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {

    Optional<UserRefreshToken> findRefreshTokenByIdx(long refreshIdx);

    void deleteByUsername(String username);

    Optional<UserRefreshToken> findByUsername(String username);


}
