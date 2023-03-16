package com.codekat.joyprotein.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.codekat.joyprotein.domain.Member;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public List<Member> findByEmail(String email) {
        List<Member> result = em.createQuery("select m from Member m where m.email = :email",Member.class)
            .setParameter("email", email).getResultList();
        return result;
    }

    public List<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name",Member.class)
            .setParameter("name", name).getResultList();
        return result;
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }


}
