package com.codekat.joyprotein.domain.items;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("protein") // 구분짓는 칼럼(속성)값
@Getter
@Setter
public class Protein extends Item {
    private int weight; // 단위 : g 
    private String tasteCode;
}
