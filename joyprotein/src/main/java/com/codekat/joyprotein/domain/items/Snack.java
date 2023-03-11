package com.codekat.joyprotein.domain.items;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("snack") // 구분짓는 칼럼(속성)값
public class Snack extends Item{
    private int quantity;
    private String tasteCode;
}
