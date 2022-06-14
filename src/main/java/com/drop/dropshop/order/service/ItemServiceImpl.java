package com.drop.dropshop.order.service;

import com.drop.dropshop.order.entity.Category;
import com.drop.dropshop.order.entity.Item;
import com.drop.dropshop.order.repository.CategoryRepository;
import com.drop.dropshop.order.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService{

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public Long saveItem(Long categoryId, Long droneId, float weight, int price) {
        Category findCategory = categoryRepository.findById(categoryId);
        //drone 정보를 받아와야함. 초기 가격을 알기 위함.

        Item item = Item.createItem(findCategory, droneId, weight);
        item.calTotalPrice(price);

        itemRepository.save(item);
        return item.getId();
    }

    @Override
    public void deleteItem(Long itemId) {
        Item item = itemRepository.findById(itemId);
        itemRepository.delete(item);
    }

    @Override
    public Item findOne(Long itemId) {
        return itemRepository.findById(itemId);
    }
}
