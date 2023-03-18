package com.codekat.joyprotein.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.codekat.joyprotein.domain.items.Item;

@Repository
public class ItemRepository {
    @PersistenceContext
    private EntityManager em;

    public Long save(Item item){
        em.persist(item);
        return item.getId();
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
        // em.createQuery("select i from Item i where i.id =:id",Item.class).setParameter("id", id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i",Item.class).getResultList();
    }

    public List<Item> findProteins(){
        return em.createQuery("select i from Item i where i.dtype = protein",Item.class).getResultList();
    }
    
}
