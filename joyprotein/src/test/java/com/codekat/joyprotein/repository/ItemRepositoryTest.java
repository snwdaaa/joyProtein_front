package com.codekat.joyprotein.repository;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.codekat.joyprotein.domain.items.Item;

@SpringBootTest
@Transactional
public class ItemRepositoryTest {
    
    @Autowired
    private ItemRepository itemRepository;
    
    @Test
    public void 아이템_저장() {
        // given
        Item item = new Item();
        item.setName("Protein Bar");
        item.setPrice(2000);
        item.setStockQuantity(50);
        item.setImgUrl("https://www.example.com/proteinbar");
        
        // when
        itemRepository.save(item);
        
        // then
        Item savedItem = itemRepository.findOne(item.getId());
        assertThat(savedItem.getName()).isEqualTo("Protein Bar");
        assertThat(savedItem).isEqualTo(item);
    }

    
    @Test
    public void 전체조회() {
        // given
        Item item1 = new Item();
        item1.setName("Protein Bar");
        item1.setPrice(2000);
        item1.setStockQuantity(50);
        item1.setImgUrl("https://www.example.com/proteinbar");
        itemRepository.save(item1);
        
        Item item2 = new Item();
        item2.setName("Protein Shake");
        item2.setPrice(2500);
        item2.setStockQuantity(30);
        item2.setImgUrl("https://www.example.com/proteinshake");
        itemRepository.save(item2);
        
        // when
        List<Item> itemList = itemRepository.findAll();
        
        // then
        assertThat(itemList).contains(item1, item2);
    }

}

