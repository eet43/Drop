package com.drop.dropshop.user.controller;

import com.drop.dropshop.user.dto.TokenDto;
import com.drop.dropshop.user.response.ResponseDetails;
import com.drop.dropshop.user.util.HttpStatusChangeInt;
import com.drop.dropshop.user.dto.AuthDto;
import com.drop.dropshop.user.dto.UserRequestDto;
import com.drop.dropshop.user.service.UserAuthService;
import com.drop.dropshop.user.service.UserService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserAuthService userAuthService;

    @PostMapping("api/user/signup")
    public String signup(@RequestBody UserRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "success signup";
    }


    @PostMapping("api/user/login")
    public ResponseEntity<?> login(@RequestBody AuthDto.LoginDTO loginDTO) throws AccessDeniedException {
        TokenDto tokenDto = userAuthService.login(loginDTO);
        int httpStatus = HttpStatusChangeInt.ChangeStatusCode("OK");
        String path = "api/user";
        ResponseDetails responseDetails = new ResponseDetails(new Date(), tokenDto, httpStatus, path);
        return new ResponseEntity<>(responseDetails, HttpStatus.OK);
    }


    @PostMapping("api/user/logout")
    public ResponseEntity<?> logoutToken(HttpServletRequest request) throws AccessDeniedException {
        userAuthService.logout(request);
        return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }


}