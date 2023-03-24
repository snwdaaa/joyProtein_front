package com.codekat.joyprotein.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.codekat.joyprotein.domain.*;
import com.codekat.joyprotein.domain.items.Protein;
import com.codekat.joyprotein.service.ItemService;
import com.codekat.joyprotein.service.MemberService;
import com.codekat.joyprotein.service.OrderItemService;
import com.codekat.joyprotein.service.OrderService;

@Controller
public class TestController {
    @Autowired
    MemberService memberService;
    @Autowired
    ItemService itemService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;

    @GetMapping(value = "/test")
    public String injectTestEntity() throws Exception {
        // Member 객체 생성
        Member member1 = new Member();
        member1.setName("John");
        member1.setEmail("john@example.com");
        member1.setAddress(new Address("123 Main St", "Los Angeles", "90001"));

        Member member2 = new Member();
        member2.setName("Jane");
        member2.setEmail("jane@example.com");
        member2.setAddress(new Address("456 First Ave", "New York", "10001"));

        Member member3 = new Member();
        member3.setName("Park");
        member3.setEmail("park@test.com");
        Address address3 = new Address("Busan", "Haeundae-gu", "345-678");
        member3.setAddress(address3);

        Long memberId = memberService.join(member1);
        member1.setCart(member1.getCart());
        memberService.join(member2);
        member2.setCart(member2.getCart());
        memberService.join(member3);
        member3.setCart(member3.getCart());

        // Protein 객체 생성
        Protein protein1 = new Protein();
        protein1.getProduct().setName("Impact Whey Protein");
        protein1.setPrice(70000);
        protein1.setStockQuantity(10000000);
        protein1.setWeight(2500);
        protein1.getProduct().setImgUrl("https://static.thcdn.com/images/large/webp//productimg/1600/1600/11571206-1224790440293489.jpg");
        protein1.getProduct().setDescription("Refreshing and fruity, this clear protein drink is packed with 20g of protein and comes in a range of delicious flavours.");
        System.out.println(protein1.getStockQuantity());

        Protein protein2 = new Protein();
        protein2.getProduct().setName("Impact Whey ISOLATE");
        protein2.setPrice(30000);
        protein2.setStockQuantity(1000000);
        protein2.setWeight(2500);
        protein2.getProduct().setImgUrl("https://static.thcdn.com/images/large/original//productimg/1600/1600/12525919-1504811124879147.jpg");
        protein2.getProduct().setDescription("Refreshing and fruity, this clear protein drink is packed with 20g of protein and comes in a range of delicious flavours.");
        System.out.println(protein2.getStockQuantity());

        Long itemId2 = itemService.saveItem(protein2);
        Long itemId1 = itemService.saveItem(protein1);

        // Cart 객체 생성
        OrderItem orderItem1 = OrderItem.createOrderItem(protein1, 30, protein1.getPrice());
        OrderItem orderItem2 = OrderItem.createOrderItem(protein2, 300, protein2.getPrice());

        orderItemService.addItemToCart(memberId, protein1,30);
        orderItemService.addItemToCart(memberId, protein2,300);

        return "redirect:/";
    }
}
