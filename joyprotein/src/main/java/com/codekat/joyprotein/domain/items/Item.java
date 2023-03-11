package com.codekat.joyprotein.domain.items;

import javax.persistence.Entity;
import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED) // 상속 구현 전략 선택
@DiscriminatorColumn(name = "dtype")
public class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
    private String imgUrl;
}
