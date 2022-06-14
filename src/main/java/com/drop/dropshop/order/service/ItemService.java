package com.drop.dropshop.order.service;

import com.drop.dropshop.order.entity.Item;

public interface ItemService {
    public Long saveItem(Long categoryId, Long droneId, float weight, int price);

    public void deleteItem(Long itemId);

    public Item findOne(Long itemId);
}
