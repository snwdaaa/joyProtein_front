package com.codekat.joyprotein.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.codekat.joyprotein.domain.*;
import com.codekat.joyprotein.domain.items.*;
import com.codekat.joyprotein.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@Transactional
@RequiredArgsConstructor
public class OrderServiceTest {
    private OrderService orderService;
    private OrderRepository orderRepository;
    @PersistenceContext
    private EntityManager em;

    @Test
    public void 상품주문() throws Exception {
        // given
        Member member = createMember("test@mm.com");
        Item item1 = createProtein("wpc프로틴");
        //when
        Long orderId = orderService.orderItem(member.getId(), item1.getId(), 10);
        //then
        Order resultOrder = orderRepository.findOne(orderId);

        Assertions.assertEquals(OrderStatus.ORDER, resultOrder.getOrderStatus(),"상품 주문시 상태는 ORDER");
        Assertions.assertEquals(10, resultOrder.getOrderItems().size(),"주문한 상품수가 정확해야한다");
        Assertions.assertEquals(10000 * 10, resultOrder.getTotalPrice(), "주문한 상품의 총 가격은 가격 * 주문수량이다.");
        Assertions.assertEquals(10000-10, item1.getStockQuantity(),"주문한 수량만큼 수량이 줄어들어야 한다.");
        Assertions.assertEquals(2500, ((Protein) resultOrder.getOrderItems().get(0).getItem()).getWeight(),"프로틴을 주문할시 무게가 제대로 나와야한다.");
    }

    private Member createMember(String email) {
        Member member = new Member();
        member.setEmail(email);
        member.setAddress(new Address("서울","312323","123-123"));
        member.setName("홍길동");
        em.persist(member);
        return member;
    }

    private Item createProtein(String name){
        Item protein = new Protein();
        protein.setImgUrl("test_url");
        protein.setPrice(10000);
        protein.setStockQuantity(10000);
        protein.setName(name);
        ((Protein) protein).setWeight(2500);
        ((Protein) protein).setTasteCode("111111");
        em.persist(protein);
        return protein;
    }
}
