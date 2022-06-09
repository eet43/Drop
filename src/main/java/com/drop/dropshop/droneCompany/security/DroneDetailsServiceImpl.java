package com.drop.dropshop.droneCompany.security;

import com.drop.dropshop.droneCompany.entity.DroneCompany;
import com.drop.dropshop.droneCompany.exception.AccessDeniedException;
import com.drop.dropshop.droneCompany.exception.ErrorCode;
import com.drop.dropshop.droneCompany.repository.DroneCompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DroneDetailsServiceImpl implements UserDetailsService {
    @Autowired
    DroneCompanyRepository droneCompanyRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<DroneCompany> droneCompany = droneCompanyRepository.findByLoginId(username);
        return new DroneDetailsImpl(droneCompany.get());
    }
}
