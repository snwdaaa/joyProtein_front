package com.codekat.joyprotein.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
    private List<Order> orders;
}
