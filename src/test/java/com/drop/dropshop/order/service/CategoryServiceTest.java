package com.drop.dropshop.order.service;

import com.drop.dropshop.order.entity.Category;
import com.drop.dropshop.order.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CategoryServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;


    @Test
    public void 카테고리_저장() throws Exception {
        //given
        Category category = new Category();
        category.setName("의류");
        category.setCost(2);

        //when
        Long saveId = categoryService.saveCategory(category);

        //then
        assertEquals(category, categoryRepository.findById(saveId));
    }


    @Test
    public void 카테고리_조회() throws Exception {
        //given
        Category category1 = new Category();
        category1.setName("의류");
        category1.setCost(2);

        Category category2 = new Category();
        category2.setName("귀중품");
        category2.setCost(3);

        //when
        List<Category> categories = categoryService.findAllWithItem();

        //then
        for (Category category : categories) {


        }
    }
}