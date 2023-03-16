package com.codekat.joyprotein.service;

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
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long order(Long memberId, Long ItemId, int count){ //  주문 상품이 한개일때만 함.
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(ItemId);
        OrderItem orderItem = new OrderItem();
        orderItem.createOrderItem(item, count, item.getPrice()); // undo
        Order order = Order.createOrder(member, orderItem);
        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void cancleOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancle();
    }
}
