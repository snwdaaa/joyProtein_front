package com.codekat.joyprotein.domain;

import javax.persistence.Embeddable;

import lombok.Getter;
@Embeddable
@Getter
public class Address {
    private String street;
    private String zipcode;
    private String detail; // 상세 주소
    protected Address(){}
    public Address(String street, String zipcode, String detail){
        this.detail = detail;
        this.zipcode = zipcode;
        this.street = street;
    }
}
