package com.drop.dropshop.order.repository;


import com.drop.dropshop.order.entity.Item;

import java.util.List;

public interface ItemRepository {

    // 상품 저장
    void save(Item item);

    // 전체 상품 조회
    List<Item> findAll();

    // 특정 상품 조회
    Item findById(Long id);

    // 특정 상품 삭제
    void delete(Item item);
}
