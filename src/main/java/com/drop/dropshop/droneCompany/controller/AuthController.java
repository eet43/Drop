package com.drop.dropshop.droneCompany.controller;

import com.drop.dropshop.droneCompany.dto.AuthDTO;
import com.drop.dropshop.droneCompany.dto.TokenDto;
import com.drop.dropshop.droneCompany.exception.AccessDeniedException;
import com.drop.dropshop.droneCompany.response.ResponseDetails;
import com.drop.dropshop.droneCompany.service.DroneCompanyAuthService;
import com.drop.dropshop.droneCompany.util.HttpStatusChangeInt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

@Api(tags = "Auth / 로그인")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final DroneCompanyAuthService authService;

    @PostMapping("/api/drone-companies/login")
    @ApiOperation(value = "드론 업체 로그인", notes = "드론 업체가 로그인을 진행합니다.")
    public ResponseEntity<?> login(@RequestBody @Valid AuthDTO.LoginDTO loginDTO) throws AccessDeniedException {
        TokenDto tokenDto = authService.login(loginDTO);
        int httpStatus = HttpStatusChangeInt.ChangeStatusCode("OK");
        String path = "/api/drone-companies";
        ResponseDetails responseDetails = new ResponseDetails(new Date(), tokenDto, httpStatus, path);
        return new ResponseEntity<>(responseDetails, HttpStatus.OK);
    }

    @PostMapping("/api/drone-companies/refreshToken")
    @ApiOperation(value = "드론 업체 새로운 토큰 발급", notes = "드론 업체에게 새로운 토큰을 발급해줍니다.")
    public ResponseEntity<?> newAccessToken(@RequestBody @Valid AuthDTO.GetNewAccessTokenDTO getNewAccessTokenDTO, HttpServletRequest request) throws AccessDeniedException {
        TokenDto tokenDto = authService.newAccessToken(getNewAccessTokenDTO, request);
        int httpStatus = HttpStatusChangeInt.ChangeStatusCode("OK");
        String path = "/api/drone-companies";
        ResponseDetails responseDetails = new ResponseDetails(new Date(), tokenDto, httpStatus, path);
        return new ResponseEntity<>(responseDetails, HttpStatus.OK);
    }

    @PostMapping("/api/drone-companies/logout")
    @ApiOperation(value = "드론 업체 로그아웃", notes = "드론 업체가 로그아웃을 진행합니다.")
    public ResponseEntity<?> logoutToken(HttpServletRequest request) throws AccessDeniedException {
        authService.logout(request);
        return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }
}
