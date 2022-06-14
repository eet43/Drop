package com.drop.dropshop.droneCompany.security;

import com.drop.dropshop.droneCompany.entity.DroneCompany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Setter
@Getter
public class DroneDetailsImpl implements UserDetails {
    private DroneCompany droneCompany;
    private String role = "DRONE_COMPANY";

    public DroneDetailsImpl(DroneCompany droneCompany) {
        this.droneCompany = droneCompany;
    }

    public DroneCompany getDroneCompany() {
        return droneCompany;
    }

    @Override
    public String getPassword() {
        return droneCompany.getLoginPassword();
    }

    @Override
    public String getUsername() {
        return droneCompany.getLoginId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // 인가
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new SimpleGrantedAuthority(this.role));
        return collection;
    }
}
