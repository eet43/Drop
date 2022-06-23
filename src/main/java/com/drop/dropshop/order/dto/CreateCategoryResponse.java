package com.drop.dropshop.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CreateCategoryResponse {
    private Long id;
    private LocalDateTime createdAt;
}
