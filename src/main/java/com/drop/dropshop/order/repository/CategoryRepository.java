package com.drop.dropshop.order.repository;

import com.drop.dropshop.order.entity.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * 2022-06-02
 * 카테고리 DB 조작 클래스
 * 1. 카테고리 등록
 * 2. 카테고리 전체 조회
 */
@Repository
public class CategoryRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Category category) {
        em.persist(category);
    }

    public List<Category> findAll() {
        return em.createQuery("select c from Category c", Category.class)
                .getResultList();
    }

    public Category findById(Long id) {
        return em.find(Category.class, id);
    }

}
