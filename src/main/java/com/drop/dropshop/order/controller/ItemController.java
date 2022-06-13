package com.drop.dropshop.order.controller;

import com.drop.dropshop.order.dto.CreateItemRequest;
import com.drop.dropshop.order.dto.CreateResponse;
import com.drop.dropshop.order.dto.GetItemResponse;
import com.drop.dropshop.order.entity.Item;
import com.drop.dropshop.order.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/api/items")
    public CreateResponse postItem(@RequestBody @Valid CreateItemRequest itemRequest) {
        Long itemId = itemService.saveItem(itemRequest.getCategoryId(), itemRequest.getDroneId(), itemRequest.getWeight(), itemRequest.getPrice());
        return new CreateResponse(itemId);
    }

    @DeleteMapping("/api/items/{id}")
    public void deleteItem(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
    }

    @GetMapping("/api/items/{id}")
    public GetItemResponse getItem(@PathVariable("id") Long id) {
        Item findItem = itemService.findOne(id);

        return new GetItemResponse(findItem.getDroneId(), findItem.getCategory(), findItem.getWeight(), findItem.getTotalPrice(), findItem.getItemCreated());
    }
}
