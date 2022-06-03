package com.drop.dropshop.order.repository;

import com.drop.dropshop.order.entity.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * 2022-06-02
 * 주문 DB 조작 클래스
 * 1. 주문 등록
 * 2. 특정 주문 조회
 * 3. 전체 주문 조회
 * 4. 주문 취소
 */

@Repository
public class OrderRepositoryImpl implements OrderRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Order order) {
        em.persist(order);
    }

    @Override
    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    @Override
    public List<Order> findAll() {
        return em.createQuery("select o from Order o", Order.class)
                .getResultList();
    }

}
