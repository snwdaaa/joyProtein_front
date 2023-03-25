package com.codekat.joyprotein.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProteinOrderDTO {
    private int quantity;
    private int weight;
    private String tasteCode;
    private Long id;
}
