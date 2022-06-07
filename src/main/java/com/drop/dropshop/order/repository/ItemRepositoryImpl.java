package com.drop.dropshop.order.repository;

import com.drop.dropshop.order.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * 2022-06-07
 * 아이템 DB 조작 클래스
 * 1. 아이템 등록
 * 2. 특정 아이템 조회
 * 3. 전체 아이템 조회
 */
@Repository
public class ItemRepositoryImpl implements ItemRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public void save(Item item) {
        em.persist(item);
    }

    @Override
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    @Override
    public Item findById(Long id) {
        return em.find(Item.class, id);
    }
}
