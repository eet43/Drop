package com.drop.dropshop.order.dto;

import com.drop.dropshop.order.entity.Item;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SimpleCategoryDto {
    @NotNull
    private String name;

    @NotNull
    private int cost;
}
