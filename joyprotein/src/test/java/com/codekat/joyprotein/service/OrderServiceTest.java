package com.codekat.joyprotein.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.codekat.joyprotein.domain.*;
import com.codekat.joyprotein.domain.items.*;
import com.codekat.joyprotein.repository.ItemRepository;
import com.codekat.joyprotein.repository.MemberRepository;
import com.codekat.joyprotein.repository.OrderItemRepository;
import com.codekat.joyprotein.repository.OrderRepository;


@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired private OrderService orderService;
    @Autowired private OrderRepository orderRepository;
    @Autowired private ItemRepository itemRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired private OrderItemRepository orderItemRepository;

    @Test
    public void 상품주문() throws Exception {
        // given
        Member member = createMember("test@mm.com");
        Protein protein = createProtein("test protein");
        Long itemId = itemRepository.save(protein);
        Long memberId = memberRepository.save(member);

        //when
        Long orderId = orderService.orderItem(memberId, itemId, 10);
        //then
        Order resultOrder = orderRepository.findOne(orderId);

        Assertions.assertEquals(OrderStatus.ORDER, resultOrder.getOrderStatus(),"상품 주문시 상태는 ORDER");
        Assertions.assertEquals(10, resultOrder.getOrderItems().get(0).getQuantity(),"주문한 상품수가 정확해야한다");
        Assertions.assertEquals(10000 * 10, resultOrder.getTotalPrice(), "주문한 상품의 총 가격은 가격 * 주문수량이다.");
        Assertions.assertEquals(10000-10, resultOrder.getOrderItems().get(0).getItem().getStockQuantity(),"주문한 수량만큼 수량이 줄어들어야 한다.");
        Assertions.assertEquals(2500, ((Protein) resultOrder.getOrderItems().get(0).getItem()).getWeight(),"프로틴을 주문할시 무게가 제대로 나와야한다.");
    }

    @Test
    public void 장바구니_상품_일괄주문() throws Exception{
        // given
        Member member = createMember("test@mm.com");
        member.setCart(new Cart());
        Long memberId = memberRepository.save(member);
        Cart cart = member.getCart();
        Protein item1 = createProtein("test protein");
        Protein item2 = createProtein("test protein2");
        itemRepository.save(item1);
        itemRepository.save(item2);
        
        //장바구니에 담기
        OrderItem orderItem1 = OrderItem.createOrderItem(item1, 10, item1.getPrice());
        OrderItem orderItem2 = OrderItem.createOrderItem(item2, 20, item1.getPrice());
        orderItemRepository.save(orderItem1);
        orderItemRepository.save(orderItem2);

        cart.addOrderItem(orderItem1);
        cart.addOrderItem(orderItem2);

        //when
        Long orderId = orderService.orderFromCart(memberId);

        // then
        Order resultOrder = orderRepository.findOne(orderId);
        assertEquals( OrderStatus.ORDER, resultOrder.getOrderStatus(), "주문 상태는 ORDER로 변경되어야 한다.");
        assertEquals( 2, resultOrder.getOrderItems().size(),"주문한 상품 종류 수가 정확해야 한다.");
        assertEquals( 10 * item1.getPrice() + 20 * item2.getPrice(), resultOrder.getCost(),"주문 가격은 가격 * 수량이다.");

    }

    private Member createMember(String email) {
        Member member = new Member();
        member.setEmail(email);
        member.setAddress(new Address("서울","312323","123-123"));
        member.setName("홍길동");
        return member;
    }

    private Protein createProtein(String name){
        Protein protein = new Protein();
        protein.getProduct().setImgUrl("test_url");
        protein.setPrice(10000);
        protein.setStockQuantity(10000);
        protein.getProduct().setName(name);
        protein.setWeight(2500);
        protein.setTasteCode("111111");
        return protein;
    }
}
