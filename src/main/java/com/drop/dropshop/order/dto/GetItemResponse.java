package com.drop.dropshop.order.dto;

import com.drop.dropshop.order.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class GetItemResponse {
    private Long droneId;

    private Category category;

    private float weight;

    private int totalPrice;

    private LocalDateTime itemCreated;
}
