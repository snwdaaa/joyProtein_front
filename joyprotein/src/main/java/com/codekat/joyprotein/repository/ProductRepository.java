package com.codekat.joyprotein.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.codekat.joyprotein.domain.items.Product;

@Repository
public class ProductRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    public Product findById(Long id) {
        return em.find(Product.class, id);
    }
    
    public List<Product> findAll() {
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    public List<Product> findByCategory(String category){
        return em.createQuery("SELECT p FROM Product p WHERE p.category = :category", Product.class).setParameter("category", category).getResultList();
    }
    
    public void save(Product product) {
        if (product.getId() == null) {
            em.persist(product);
        } else {
            em.merge(product);
        }
    }
    
    public void delete(Long id) {
        Product product = em.find(Product.class, id);
        em.remove(product);
    }
    
}
