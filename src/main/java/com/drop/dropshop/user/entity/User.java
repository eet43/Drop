package com.drop.dropshop.user.entity;

import com.drop.dropshop.user.dto.UserRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String username;
    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Column(nullable = false, length = 20)
    private String email;

    @Column(nullable = false, length = 30)
    private String address;

    @Column
    @Enumerated(value = EnumType.STRING)
    private UserRole role;


    public User(String username, String password, String name, String phoneNumber, String email, String address, UserRole role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.role = role;
    }

    public User(UserRequestDto requestDto) {
            this.username = requestDto.getUsername();
            this.password = requestDto.getPassword();
            this.name = requestDto.getName();
            this.phoneNumber = requestDto.getPhoneNumber();
            this.email = requestDto.getEmail();
            this.address = requestDto.getAddress();
            this.role = requestDto.getRole();
        }
    }
