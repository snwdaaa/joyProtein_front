package com.codekat.joyprotein.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import com.codekat.joyprotein.domain.items.Item;
import com.codekat.joyprotein.domain.items.Protein;
import com.codekat.joyprotein.repository.TasteRepository;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "orderItem_id")
    private Long id;
    
    private String name;
    private int price;
    private int quantity;

    // 연관 관계 변수들.
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    //== 생성 메서드 ==//
    public static OrderItem createOrderItem(Item item, int quantity, int price) throws Exception{
        OrderItem orderItem = new OrderItem();
        orderItem.item = item;
        orderItem.quantity = quantity;
        orderItem.price = price;
        item.removeStock(quantity);
        if (item instanceof Protein) {
            orderItem.setName(item.getProduct().getName()+ " (맛: "+TasteRepository.getTaste(((Protein) item).getTasteCode()) + ", 용량: "+ ((Protein) item).getWeight() + "g)");
        }
        return orderItem;
    }

    public void cancle(){
        item.addStock(this.quantity);
    }

    //== 연관 관계 메서드 ==//
    
    
}
