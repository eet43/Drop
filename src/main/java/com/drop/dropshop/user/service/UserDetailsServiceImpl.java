package com.drop.dropshop.user.service;

import com.drop.dropshop.user.entity.User;
import com.drop.dropshop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        // 해당 아이디로 사용자를 찾을 수 없는 경우 만들어 둔 에러를 던짐
        if (user.isEmpty()) {
            throw new IllegalArgumentException("요청한 아이디 또는 비밀번호를 찾을 수 없습니다.");
        }
        return new UserDetailsImpl(user.get());
    }
}