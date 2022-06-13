package com.drop.dropshop.order.dto;

import com.drop.dropshop.order.entity.Category;
import com.drop.dropshop.order.entity.Item;
import com.drop.dropshop.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class CreateItemRequest {
    private Long droneId;
    private Long categoryId;
    private float weight;
    private int price;
}
