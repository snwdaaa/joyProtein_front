package com.codekat.joyprotein.domain.items;

import javax.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 상속 구현 전략 선택
public class Product {
    @Id @GeneratedValue
    @Column(name = "product_id")
    private Long id;
    private String name;
    private String imgUrl;
    private String description;
    private String category;

    @OneToMany(mappedBy = "product")
    private List<Item> items = new ArrayList<Item>();

    // 연관관계 메소드
    public void addItem(Item item){
        items.add(item);
        item.setProduct(this);
    }
}
