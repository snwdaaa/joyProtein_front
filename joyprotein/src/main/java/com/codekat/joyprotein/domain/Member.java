package com.codekat.joyprotein.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    
    private String name;
    private String email;
    @Embedded
    private Address address;

    // 연관관계 변수들
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<Order>();

    @OneToOne(mappedBy = "member",cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public void setCart(Cart cart){
        this.cart = cart;
        cart.setMember(this);
    }
}
