package com.drop.dropshop.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService{

    //userRepository 받아야함

    @Override
    @Transactional
    public Long order(Long userId, Long itemId) {
    }
}
