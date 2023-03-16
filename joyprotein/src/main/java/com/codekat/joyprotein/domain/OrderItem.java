package com.codekat.joyprotein.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.codekat.joyprotein.domain.items.Item;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "orderItem_id")
    private Long id;

    private int price;
    private int quantity;

    // 연관 관계 변수들.
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    //== 생성 메서드 ==//
    public void createOrderItem(Item item, int quantity, int price){
        
    }



    //== 연관 관계 메서드 ==//

    
}
