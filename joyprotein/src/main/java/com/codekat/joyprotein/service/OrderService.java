package com.codekat.joyprotein.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codekat.joyprotein.domain.*;
import com.codekat.joyprotein.domain.items.Item;
import com.codekat.joyprotein.repository.ItemRepository;
import com.codekat.joyprotein.repository.MemberRepository;
import com.codekat.joyprotein.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long orderItem(Long memberId, Long ItemId, int count) throws Exception{ //  주문 상품이 한개일때만 함.
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(ItemId);
        OrderItem orderItem = new OrderItem();
        OrderItem.createOrderItem(item, count, item.getPrice()); // undo
        Order order = Order.createOrder(member, orderItem);
        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public Long orderFromCart(Long memberId) throws Exception {
        Member member = memberRepository.findOne(memberId);
        Cart cart = member.getCart();
        List<OrderItem> orderItems = cart.getOrderItems();
        if (orderItems.size() == 0) {
            throw new Exception("장바구니에 아이템이 없습니다.");
        }
        Order order = Order.createOrder(member, orderItems);
        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void cancleOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancle();
    }
}
