package com.drop.dropshop.user.controller;
import com.drop.dropshop.user.dto.UserRequestDto;
import com.drop.dropshop.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/user/signup")
    public String registerUser(@RequestBody UserRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "success signup";
    }


}