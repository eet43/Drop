package com.drop.dropshop.order.service;

public interface ItemService {
    public Long saveItem(Long categoryId, Long droneId, float weight, float distance);
}
