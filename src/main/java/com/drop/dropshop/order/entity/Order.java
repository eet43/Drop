package com.drop.dropshop.order.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "delivery_id")
    private Long deliveryId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime orderDate;

    public void addItem(Item item) {
        this.setItem(item);
        item.setOrder(this);
    }


    //==생성 메서드==//
    public static Order createOrder(Item item, Long memberId, Long deliveryId) {
        Order order = new Order();
        order.addItem(item);
        order.setMemberId(memberId);
        order.setDeliveryId(deliveryId);
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());

        return order;
    }

    //==비즈니스 로직==//

    public void cancelOrder() {
        //배송 시작? 취소? 에 대한 정보 알아야 함.
        // if(배송 시작 전이라면 ?)
        this.setStatus(OrderStatus.CANCEL);
        // 배송, 드론 도메인에 알려줘야 함.
    }


}




