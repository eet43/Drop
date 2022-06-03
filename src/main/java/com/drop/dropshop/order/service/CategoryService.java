package com.drop.dropshop.order.service;

import com.drop.dropshop.order.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Transactional
    public Long



}
