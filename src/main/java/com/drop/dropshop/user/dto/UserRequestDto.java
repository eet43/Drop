package com.drop.dropshop.user.dto;

import com.drop.dropshop.user.entity.UserRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserRequestDto {
    private String username;
    private String password;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
//    private String role;
    private UserRole role;
}
