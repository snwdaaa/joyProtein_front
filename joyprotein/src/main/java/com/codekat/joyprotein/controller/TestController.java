package com.codekat.joyprotein.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.codekat.joyprotein.domain.*;
import com.codekat.joyprotein.domain.items.Protein;
import com.codekat.joyprotein.repository.TasteRepository;
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
        protein1.getProduct().setName("임팩트 웨이 프로틴");
        protein1.getProduct().setCategory("protein");
        protein1.setPrice(70000);
        protein1.setStockQuantity(10000000);
        protein1.setWeight(2500);
        protein1.setTasteCode("111111");
        protein1.getProduct().setImgUrl("https://static.thcdn.com/images/large/webp//productimg/1600/1600/11571206-1224790440293489.jpg");
        protein1.getProduct().setDescription("마이프로틴의 베스트 셀러인 임팩트 웨이 프로틴은 건강하게 기른 소의 원유를 건조, 필터, 분말화의 세분화된 공정을 통하여 제조한 유청 단백질입니다.");
        System.out.println(protein1.getStockQuantity());

        Protein protein11 = new Protein();
        protein11.setProduct(protein1.getProduct());
        protein11.setPrice(30000);
        protein11.setStockQuantity(10000000);
        protein11.setWeight(1000);
        protein11.setTasteCode("111111");

        Protein protein111 = new Protein();
        protein111.setProduct(protein1.getProduct());
        protein111.setPrice(70000);
        protein111.setStockQuantity(10000000);
        protein111.setWeight(2500);
        protein111.setTasteCode("333333");

        Protein protein2 = new Protein();
        protein2.getProduct().setName("임팩트 웨이 아이솔레이트");
        protein2.getProduct().setCategory("protein");
        protein2.setPrice(100000);
        protein2.setStockQuantity(1000000);
        protein2.setTasteCode("111111");
        protein2.setWeight(2500);
        protein2.getProduct().setImgUrl("https://static.thcdn.com/images/xsmall/webp//productimg/original/10530911-1164889444408346.jpg");
        protein2.getProduct().setDescription("임팩트 웨이 아이솔레이트는 고함량의 단백질 섭취를 가능하게 하고, 90%이상의 단백질로 구성되어있으며 다른 웨이프로틴 파우더와 달리 1회 제공량에 낮은 지방(1g 이하)을 포함하고 있습니다. ");
        System.out.println(protein2.getStockQuantity());


        Protein protein3 = new Protein();
        protein3.getProduct().setName("클리어 웨이 아이솔레이트");
        protein3.getProduct().setCategory("protein");
        protein3.setPrice(130000);
        protein3.setStockQuantity(1000000);
        protein3.setTasteCode("333333");
        protein3.setWeight(1000);
        protein3.getProduct().setImgUrl("https://static.thcdn.com/images/large/original//productimg/1600/1600/12457913-3554790136840897.jpg");
        protein3.getProduct().setDescription("클리어 웨이 아이솔레이트는 고품질의 가수분해 분리유청단백을 사용하였습니다. 기존 제품의 우유 맛과 음용감을 제거하여 산뜻하고 깔끔한 단백질 보충을 돕습니다.");


        Protein protein4 = new Protein();
        protein4.getProduct().setName("웨이트 게이너 블렌더");
        protein4.getProduct().setCategory("protein");
        protein4.setPrice(100000);
        protein4.setStockQuantity(1000000);
        protein4.setTasteCode("111111");
        protein4.setWeight(50000);
        protein4.getProduct().setImgUrl("https://static.thcdn.com/images/large/original//productimg/1600/1600/10529988-1034951863070437.jpg");
        protein4.getProduct().setDescription("웨이트 게이너 블렌더는 1회 제공량당 31g의 단백질과 50g의 탄수화물과, 388kcal의 열량을 함유하고 있어 체중 증가와 고강도 운동 이후의 회복에 도움을 줄 수 있습니다.");


        Long itemId111 = itemService.saveItem(protein111);
        Long itemId11 = itemService.saveItem(protein11);
        Long itemId4 = itemService.saveItem(protein4);
        Long itemId3 = itemService.saveItem(protein3);
        Long itemId2 = itemService.saveItem(protein2);
        Long itemId1 = itemService.saveItem(protein1);

        // Cart 객체 생성
        OrderItem orderItem1 = OrderItem.createOrderItem(protein1, 30, protein1.getPrice());
        orderItem1.setName(protein1.getProduct().getName()+ ") 맛: "+TasteRepository.getTaste(protein1.getTasteCode()) + ", 용량: "+ protein1.getWeight() + "g");
        OrderItem orderItem2 = OrderItem.createOrderItem(protein2, 300, protein2.getPrice());

        orderItemService.addItemToCart(memberId, protein1,30);
        orderItemService.addItemToCart(memberId, protein2,300);

        return "redirect:/";
    }
}
