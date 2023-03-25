package com.codekat.joyprotein.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.codekat.joyprotein.domain.items.Item;
import com.codekat.joyprotein.domain.items.Protein;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;


@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;
    @PersistenceContext
    private EntityManager em;

    @Test
    void 아이템_저장() {
        // given
        Item item = new Item();
        item.getProduct().setName("아이템1");
        item.setPrice(10000);
        item.setStockQuantity(10);
        item.getProduct().setImgUrl("https://example.com/item1.png");

        itemService.saveItem(item);

        // then
        List<Item> items = itemService.findItems();
        if (items != null) {
            assertEquals(1, items.size());
            assertEquals("아이템1", items.get(0).getProduct().getName());
            assertEquals(10000, items.get(0).getPrice());
            assertEquals(10, items.get(0).getStockQuantity());
        } else {
            fail("items list is null");
        }
    }

    @Test
    void 프로틴_저장() {
        // given
        Protein protein = createProtein("test protein");
        //when
        itemService.saveItem(protein);
    
        //then
        Protein resultProtein = (Protein) itemService.findOne(protein.getId());
    
        assertEquals(protein, resultProtein);
        assertEquals(2500, resultProtein.getWeight());
        assertEquals("111111", resultProtein.getTasteCode());
    }

    @Test
    void 프로틴_옵션선택후_가져오기() throws Exception{
        // given
        Protein protein = createProtein("test protein");
        itemService.saveItem(protein);

        //when
        Item item = itemService.takeProtein(protein.getProduct().getId(), protein.getWeight(), protein.getTasteCode());

        //then
        assertEquals(protein, item);
        assertEquals(2500, ((Protein) item).getWeight());
        assertEquals("111111", ((Protein) item).getTasteCode());
    }

    // @Test
    // void 아이템_수정() {
    //     // given
    //     Item item = new Item();
    //     item.getProduct().setName("아이템1");
    //     item.setPrice(10000);
    //     item.setStockQuantity(10);
    //     item.getProduct().setImgUrl("https://example.com/item1.png");
    //     itemService.saveItem(item);

    //     // when
    //     itemService.updateItem(item.getId(), "수정된 아이템1", 20000);

    //     // then
    //     Item updatedItem = itemService.findOne(item.getId());
    //     assertThat(updatedItem.getProduct().getName()).isEqualTo("수정된 아이템1");
    //     assertThat(updatedItem.getPrice()).isEqualTo(20000);
    //     assertThat(updatedItem.getStockQuantity()).isEqualTo(20);
    //     assertThat(updatedItem.getProduct().getImgUrl()).isEqualTo("https://example.com/modified_item1.png");
    // }

    
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
