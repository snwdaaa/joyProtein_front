package com.codekat.joyprotein.repository;

/**
 *  이거 그냥 임시로 만든 거임.
 */
public class TasteRepository {
    public static String getTaste(String tasteCode){
        switch (tasteCode) {
            case "111111":
                return "Chocolate";
            case "222222":
                return "Vanilla";
            case "333333":
                return "Strawberry";
            case "444444":
                return "Banana";
            default:
                return "정의되지 않은 맛";
        }
    }
}