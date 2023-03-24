package com.codekat.joyprotein.domain.items;

import javax.persistence.Entity;
import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 상속 구현 전략 선택
@DiscriminatorColumn(name = "dtype")
public class Item{
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private int price;
    private int stockQuantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product = new Product();

    // 연관 관계 변수들.
    

    //== 연관관계 편이 양방향 메소드 ==//
    
    
    //==비즈니스 로직==// 
    // 주문 -> 스택감소 , 주문취소 -> 스택 그만큼 추가
    public void removeStock(int quantity) throws Exception{
        if(stockQuantity < quantity){
            throw new Exception("not enough stock");
        }
        stockQuantity-=quantity;
    }
    public void addStock(int quantity){
        stockQuantity+=quantity;
    }
}
