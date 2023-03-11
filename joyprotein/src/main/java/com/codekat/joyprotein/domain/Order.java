package com.codekat.joyprotein.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private int cost;
    private LocalDateTime orderTime;

    // 연관관계 변수들
    @ManyToOne
    @JoinColumn(name = "member_id") //FK = Member테이블의 member_id
    private Member member;

    @OneToMany(mappedBy = "orderItem")
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    //== 연관관계 편이 양방향 메소드 ==//
    /*
     * 보통 order 추가할때 member.getOrders().add(order); order.setMember(member); 하지만,
     * 아래로 오버로딩해주면 order.setMember(order) 하면 끝남. === 보통 member가 order를 추가하므로. 없어도 되지만 있으면 더 편함 
     */
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }
    // public addOrderItem(Order)
    

    //== 생성 메서드 ==//
    public static void createOrder() {
        
    }

    //== 비즈니스 로직 메서드 ==//
    /*
     * 주문 취소
     */
    public void cancle() {
        
    }



    //== 조회 로직 ==//
    public int getTotalPrice() {
        return 0;
    }

}
