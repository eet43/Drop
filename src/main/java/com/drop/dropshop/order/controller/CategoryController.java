package com.drop.dropshop.order.controller;

import com.drop.dropshop.order.dto.CreateOrderResponse;
import com.drop.dropshop.order.dto.SimpleCategoryDto;
import com.drop.dropshop.order.dto.Result;
import com.drop.dropshop.order.dto.SimpleCategories;
import com.drop.dropshop.order.entity.Category;
import com.drop.dropshop.order.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 2022-06-10
 * 카테고리 Url 컨트롤러
 * 1. 전체 카테고리 조회
 * 2. 특정 카테고리 등록 (ID)
 */


@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/api/categories")
    public Result findCategories() {
        List<Category> categories = categoryService.findAll();

        List<SimpleCategories> collect = categories.stream()
                .map(c -> new SimpleCategories(c.getId(), c.getName()))
                .collect(Collectors.toList()); //DTO 객체 연결
        return new Result(collect);
    }

    @PostMapping("/api/categories")
    public CreateOrderResponse postCategory(@RequestBody @Valid SimpleCategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setCost(categoryDto.getCost());

        Long id = categoryService.saveCategory(category);

        return new CreateOrderResponse(id);
    }
}
