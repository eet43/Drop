package com.drop.dropshop.order.service;

import com.drop.dropshop.order.entity.Category;
import com.drop.dropshop.order.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long saveCategory(Category category) {
        categoryRepository.save(category);
        return category.getId();
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public List<Category> findAllWithItem() {
        return categoryRepository.findAllWithItem();}

}
