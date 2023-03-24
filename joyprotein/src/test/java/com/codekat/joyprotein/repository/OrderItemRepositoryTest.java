package com.codekat.joyprotein.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.codekat.joyprotein.domain.*;
import com.codekat.joyprotein.domain.items.*;


@SpringBootTest
@Transactional
public class OrderItemRepositoryTest {
    @Autowired private OrderItemRepository orderItemRepository;
    @Autowired private ItemRepository itemRepository;

    @Test
    public void 주문상품_등록() throws Exception{
        Protein protein = createProtein("wpc프로틴");
        itemRepository.save(protein);
        OrderItem orderItem = OrderItem.createOrderItem(protein, 10, protein.getPrice());
        Long orderItemId = orderItemRepository.save(orderItem);
        OrderItem resultOrderItem = orderItemRepository.findOne(orderItemId);
        Assertions.assertEquals(orderItem, resultOrderItem);
        Assertions.assertEquals(10000-10, resultOrderItem.getItem().getStockQuantity(),"주문한 수량만큼 수량이 줄어들어야 한다.");
        Assertions.assertEquals(2500, ((Protein)resultOrderItem.getItem()).getWeight(),"프로틴을 주문할시 무게가 제대로 나와야한다.");
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
