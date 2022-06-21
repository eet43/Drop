package com.drop.dropshop.user.security;

import com.drop.dropshop.user.dto.AuthDto;
import com.drop.dropshop.user.service.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private String secretKey = "a1b3secretkey";

    // 토큰 유효시간 30분
    private long accessExpireTime = 30 * 60 * 1000L;

    private final UserDetailsServiceImpl userDetailsService;

    public String createAccessToken(AuthDto.LoginDTO loginDTO) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("type", "token");

        Map<String, Object> payloads = new HashMap<>();
        // login 시 들어오는 데이터 중 user아이디를 받아 payload 부분에 추가
        // payloads.put("지정할_키_값", loginDTO.get으로 가져올 데이터());
        payloads.put("userId", loginDTO.getUsername());

        // 토큰을 생성하는 현재 시간을 받아옴
        Date expiration = new Date();
        // 현재 시간에 위에서 지정한 access token 만료 시간을 더해서 토큰 유효 시간을 지정
        expiration.setTime(expiration.getTime() + accessExpireTime);

        // 위에서 만든 header와 payload 를 가지고 jwt 생성
        String jwt = Jwts
                .builder()
                .setHeader(headers)           // header
                .setClaims(payloads)          // payload
                .setSubject("user")           // token 용도
                .setExpiration(expiration)    // 토큰 만료 시간
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        System.out.println(jwt);
        return jwt;
    }

    public Map<String, String> createRefreshToken(AuthDto.LoginDTO loginDTO) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("type", "token");

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("companyId", loginDTO.getUsername());

        Date expiration = new Date();
        expiration.setTime(expiration.getTime() + accessExpireTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        String refreshTokenExpirationAt = simpleDateFormat.format(expiration);

        String jwt = Jwts
                .builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setSubject("user")
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        Map<String, String> result = new HashMap<>();
        result.put("refreshToken", jwt);
        result.put("refreshTokenExpirationAt", refreshTokenExpirationAt);
        return result;
    }

    public String getUserInfo(String token) {
        // token 데이터를 시크릿 키를 이용하여 파싱하여 payload 안에 있는 userId를 가져옴
        return (String) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("userId");
    }

    public Authentication getAuthentication(String token) {
        // 위의 메소드를 이용하여 id 를 가져오고, 이 아이디를 5번에서 만들었던 서비스로 넘겨 사용자를 받아옴
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserInfo(token));
        // authentication 을 만들어서 넘겨줌
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("token");
    }

    public boolean validateJwtToken(ServletRequest request, String authToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            // 잘못된 JWT 구조
            request.setAttribute("exception", "MalformedJwtException");
        } catch (ExpiredJwtException e) {
            // JWT 토큰 유효시간이 만료됨
            request.setAttribute("exception", "ExpiredJwtException");
        } catch (UnsupportedJwtException e) {
            // JWT 가 예상하는 형식과 다른 형식이거나 구성
            request.setAttribute("exception", "UnsupportedJwtException");
        } catch (IllegalStateException e) {
            // JWT 의 서명실패(변조 데이터)
            request.setAttribute("exception", "IllegalStateException");
        } catch (IllegalArgumentException e) {
            // 불법적이거나 부적절한 인수를 받음
            request.setAttribute("exception", "IllegalArgumentException");
        }
        return false;
    }
}
