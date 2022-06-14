package com.drop.dropshop.user.controller;

import com.drop.dropshop.user.dto.UserRequestDto;
import com.drop.dropshop.user.entity.User;
import com.drop.dropshop.user.repository.UserRepository;
import com.drop.dropshop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/user/signup")
    public String registerUser(@RequestBody UserRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "success signup";
    }


    @GetMapping("/user/login")
    public String userLogin() {
        return "success login";
    }


    @GetMapping("/user/logout")
    public String userLogout() {
        return "success logout";
    }
}