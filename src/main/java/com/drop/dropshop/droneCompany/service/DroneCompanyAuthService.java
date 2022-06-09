package com.drop.dropshop.droneCompany.service;

import com.drop.dropshop.droneCompany.dto.AuthDTO;
import com.drop.dropshop.droneCompany.entity.RefreshToken;
import com.drop.dropshop.droneCompany.dto.TokenDto;
import com.drop.dropshop.droneCompany.exception.AccessDeniedException;
import com.drop.dropshop.droneCompany.exception.ErrorCode;
import com.drop.dropshop.droneCompany.repository.DroneCompanyRefreshTokenRepository;
import com.drop.dropshop.droneCompany.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DroneCompanyAuthService {

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final DroneCompanyRefreshTokenRepository droneCompanyRefreshTokenRepository;

    /**
     * 드론 업체 로그인
     * 로그인 성공 시 jwt token을 만드는 createTokenReturn 을 호출합니다.
     */
    public TokenDto login(AuthDTO.LoginDTO loginDTO) throws AccessDeniedException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getCompanyId(), loginDTO.getCompanyPassword())
            );
        }catch (Exception e) {
            throw new AccessDeniedException("로그인 실패", ErrorCode.UNAUTHORIZED, "요청 받은 아이디 또는 비밀번호가 맞지 않습니다.");
        }

        Map createToken = createTokenReturn(loginDTO);
        Object accessToken = createToken.get("accessToken");
        Object refreshIdx = createToken.get("refreshIdx");
        return new TokenDto(accessToken, refreshIdx);
    }

    /**
     * refresh token 을 이용하여 새로운 token 을 발급
     */
    public TokenDto newAccessToken(AuthDTO.GetNewAccessTokenDTO getNewAccessTokenDTO, HttpServletRequest request) throws AccessDeniedException {
        String refreshToken = null;
        Optional<RefreshToken> refreshTokenData = droneCompanyRefreshTokenRepository.findRefreshTokenByIdx(getNewAccessTokenDTO.getRefreshIdx());

        if (refreshTokenData.isPresent()) {
            refreshToken = refreshTokenData.get().getRefreshToken();
        } else {
            throw new AccessDeniedException("토큰 재발급 실패", ErrorCode.EXPIRED_JWT, "refresh token 이 존재하지 않습니다.");
        }

        // AccessToken은 만료되었지만 RefreshToken은 만료되지 않은 경우
        if (jwtProvider.validateJwtToken(request, refreshToken)) {
            String companyId = jwtProvider.getUserInfo(refreshToken);
            AuthDTO.LoginDTO loginDTO = new AuthDTO.LoginDTO();
            loginDTO.setCompanyId(companyId);

            Map createToken = createTokenReturn(loginDTO);
            Object accessToken = createToken.get("accessToken");
            Object refreshIdx = createToken.get("refreshIdx");
            return new TokenDto(accessToken, refreshIdx);
        } else {
            throw new AccessDeniedException("토큰 재발급 실패", ErrorCode.EXPIRED_JWT, "refresh token 이 만료되었습니다.");
        }
    }

    /**
     * jwt token 생성 후 반환
     */
    private Map<String, String> createTokenReturn(AuthDTO.LoginDTO loginDTO) {
        Map result = new HashMap();

        String accessToken = jwtProvider.createAccessToken(loginDTO);
        String refreshToken = jwtProvider.createRefreshToken(loginDTO).get("refreshToken");
        String refreshTokenExpirationAt = jwtProvider.createRefreshToken(loginDTO).get("refreshTokenExpirationAt");

        RefreshToken refreshTokenData = new RefreshToken(loginDTO.getCompanyId(), accessToken, refreshToken, refreshTokenExpirationAt);

        droneCompanyRefreshTokenRepository.save(refreshTokenData);

        result.put("accessToken", accessToken);
        result.put("refreshIdx", refreshTokenData.getIdx());
        return result;
    }
}
