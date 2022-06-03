package com.drop.dropshop.order.repository;

import com.drop.dropshop.order.entity.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 2022-06-02
 * 카테고리 DB 조작 클래스
 * 1. 카테고리 등록
 */
@Repository
public class CategoryRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Category category) {
        em.persist(category);
    }

}
