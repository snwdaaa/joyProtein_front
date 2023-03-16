package com.codekat.joyprotein.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codekat.joyprotein.domain.items.Item;
import com.codekat.joyprotein.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        this.itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity, String imgUrl){
        Item item = this.itemRepository.findOne(itemId);
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);
        item.setImgUrl(imgUrl);
    }

    public Item findOne(Long id){
        return this.itemRepository.findOne(id);
    }

    public List<Item> findItems(){
        return this.itemRepository.findAll();
    }
}
