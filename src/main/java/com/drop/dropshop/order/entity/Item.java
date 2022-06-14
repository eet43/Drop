package com.drop.dropshop.order.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @Column(name = "drone_id")
    private Long droneId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(mappedBy = "item")
    private Order order;

    private float weight;

    private int totalPrice;

    private LocalDateTime itemCreated;

    public void addCategory(Category category) {
        this.category = category;
        category.getItems().add(this);
    }

    //==생성 로직==//
    public static Item createItem(Category category, Long droneId, float weight) {
        Item item = new Item();
        item.addCategory(category);
        item.setDroneId(droneId);
        item.setWeight(weight);
        item.setItemCreated(LocalDateTime.now());

        return item;
    }

    //==비즈니스 로직==//
    /**
     * 배송 가격 계산
     */
    public int calTotalPrice(int price) {
        int value = (int) (price * getWeight() * category.getCost());
        setTotalPrice(value);
        return value;
    }




}
