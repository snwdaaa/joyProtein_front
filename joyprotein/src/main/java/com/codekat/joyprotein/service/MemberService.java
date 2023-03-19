package com.codekat.joyprotein.service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codekat.joyprotein.domain.Cart;
import com.codekat.joyprotein.domain.Member;
import com.codekat.joyprotein.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        validateOrverlapMember(member);
        memberRepository.save(member);
        Cart cart = new Cart();
        member.setCart(cart);
        return member.getId();
    }

    private void validateOrverlapMember(Member member){ // email must be unique
        List<Member> result= this.memberRepository.findByEmail(member.getEmail());
        if (!result.isEmpty()) {
            throw new IllegalStateException("member already exists");//throw
        }
        // pass
    }

    public Member findOne(Long id){
        return memberRepository.findOne(id);
    }

}
