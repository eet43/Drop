package com.drop.dropshop.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TokenDto {

    private final Object accessToken;
    private final Object refreshIdx;
}