package com.drop.dropshop.user.service;

import com.drop.dropshop.user.dto.TokenDto;
import com.drop.dropshop.user.entity.UserRefreshToken;
import com.drop.dropshop.user.dto.AuthDto;
import com.drop.dropshop.user.repository.UserRefreshTokenRepository;
import com.drop.dropshop.user.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAuthService {

    private final JwtTokenProvider jwtProvider;
    @Autowired
    @Qualifier("userAuthenticationManagerBean") private AuthenticationManager authenticationManager;
    private final UserRefreshTokenRepository userRefreshTokenRepository;

    /**
     * 드론 업체 로그인
     * 로그인 성공 시 jwt token을 만드는 createTokenReturn 을 호출합니다.
     */
    public TokenDto login(AuthDto.LoginDTO loginDTO) throws AccessDeniedException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
            );
        } catch (Exception e) {
            throw new AccessDeniedException("로그인 실패,요청 받은 아이디 또는 비밀번호가 맞지 않습니다.");
        }

        Map createToken = createTokenReturn(loginDTO);
        Object accessToken = createToken.get("accessToken");
        Object refreshIdx = createToken.get("refreshIdx");
        return new TokenDto(accessToken, refreshIdx);
    }

    /**
     * refresh token 을 이용하여 새로운 token 을 발급
     */
    public TokenDto newAccessToken(AuthDto.GetNewAccessTokenDTO getNewAccessTokenDTO, HttpServletRequest request) throws AccessDeniedException {
        String refreshToken = null;
        Optional<UserRefreshToken> refreshTokenData = userRefreshTokenRepository.findRefreshTokenByIdx(getNewAccessTokenDTO.getRefreshIdx());

        if (refreshTokenData.isPresent()) {
            refreshToken = refreshTokenData.get().getRefreshToken();
        } else {
            throw new AccessDeniedException("토큰 재발급 실패 refresh token 이 존재하지 않습니다.");
        }

        // AccessToken은 만료되었지만 RefreshToken은 만료되지 않은 경우
        if (jwtProvider.validateJwtToken(request, refreshToken)) {
            String username = jwtProvider.getUserInfo(refreshToken);
            AuthDto.LoginDTO loginDTO = new AuthDto.LoginDTO();
            loginDTO.setUsername(username);

            Map createToken = createTokenReturn(loginDTO);
            Object accessToken = createToken.get("accessToken");
            Object refreshIdx = createToken.get("refreshIdx");
            return new TokenDto(accessToken, refreshIdx);
        } else {
            throw new AccessDeniedException("토큰 재발급 실패 refresh token 이 만료되었습니다.");
        }
    }

    /**
     * jwt token 생성 후 반환
     */
    private Map<String, String> createTokenReturn(AuthDto.LoginDTO loginDTO) {
        Map result = new HashMap();

        String accessToken = jwtProvider.createAccessToken(loginDTO);
        String refreshToken = jwtProvider.createRefreshToken(loginDTO).get("refreshToken");
        String refreshTokenExpirationAt = jwtProvider.createRefreshToken(loginDTO).get("refreshTokenExpirationAt");

        UserRefreshToken refreshTokenData = new UserRefreshToken(loginDTO.getUsername(), accessToken, refreshToken, refreshTokenExpirationAt);

        userRefreshTokenRepository.findByUsername(loginDTO.getUsername()).ifPresent(dummy -> {
                    userRefreshTokenRepository.deleteByUsername(loginDTO.getUsername());
                    userRefreshTokenRepository.flush();
                }
        );
        userRefreshTokenRepository.save(refreshTokenData);

        result.put("accessToken", accessToken);
        result.put("refreshIdx", refreshTokenData.getIdx());
        return result;
    }

    /**
     * 드론 업체 로그아웃
     */
    public void logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        userRefreshTokenRepository.deleteByUsername(userDetails.getUsername());
    }
}
