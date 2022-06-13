package com.drop.dropshop.droneCompany.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class DroneSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtProvider jwtProvider;

    @Bean
    public PasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
            // rest api이므로 csrf 보안이 필요없으므로 disable처리
            .csrf().disable()
            // jwt token으로 인증하므로 stateless 하도록 처리
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/api/drone-companies/login").permitAll()
            .antMatchers("/api/drone-companies/logout").permitAll()
            .antMatchers(HttpMethod.POST, "/api/drone-companies").permitAll()
            .antMatchers("/api/drone-companies*").hasRole("ADMIN")
            .antMatchers("/api/drone-companies*/**").hasRole("ADMIN")
            .antMatchers("/api/drone-models*").hasRole("ADMIN")
            .antMatchers("/api/drone-models*/**").hasRole("ADMIN")
            .antMatchers("/api/drone-companies/**").hasRole("DRONE_COMPANY")
            .antMatchers("/app/drones").permitAll()
            .anyRequest().permitAll()
            .and()
            .exceptionHandling()
            .and()
            .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
    }

}
