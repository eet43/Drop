package com.drop.dropshop.order.repository;

import com.drop.dropshop.order.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
