package com.drop.dropshop.user.service;

import com.drop.dropshop.user.dto.UserRequestDto;
import com.drop.dropshop.user.entity.User;
import com.drop.dropshop.user.entity.UserRole;
import com.drop.dropshop.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserRequestDto requestDto) {
        String username = requestDto.getUsername();
        // 회원 ID 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }
        // 패스워드 인코딩
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();
        String name = requestDto.getName();
        String phoneNumber = requestDto.getPhoneNumber();
        String address = requestDto.getAddress();

        // 사용자 ROLE 확인
        UserRole role = UserRole.USER;
        User user = new User(username, password, name, phoneNumber, email, address, role);
        userRepository.save(user);
    }


}
