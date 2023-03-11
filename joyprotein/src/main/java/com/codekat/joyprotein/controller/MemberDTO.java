package com.codekat.joyprotein.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
    private String name;
    private String email;
    private String detail;
    private String street;
    private String zipcode;
}