package com.codekat.joyprotein.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO {
    // public CartItemDTO(Long id, String name, String imgUrl, int price, int quantity) {
    // }
    public CartItemDTO(Long id, String name, String imgUrl, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.price = price;
        this.quantity = quantity;
    }
    private Long id;
    private String name;
    private String imgUrl;
    private int price;
    private int quantity;
}
