package com.drop.dropshop.order.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    private int totalPrice;


    //==비즈니스 로직==//
    /**
     * 배송 가격 계산
     */
    public int getTotalPrice(int price, double weight, double distance) {
        return (int)(price * weight * distance * category.getCost());
    }





}
