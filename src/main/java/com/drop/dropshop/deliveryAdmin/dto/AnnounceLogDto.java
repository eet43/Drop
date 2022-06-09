package com.drop.dropshop.deliveryAdmin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AnnounceLogDto {
    private String orderId;
    private String phoneNumber;
    private String currentLocation;
}
