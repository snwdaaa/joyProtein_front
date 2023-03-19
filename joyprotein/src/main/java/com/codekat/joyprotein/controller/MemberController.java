package com.codekat.joyprotein.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import com.codekat.joyprotein.domain.Address;
import com.codekat.joyprotein.domain.Cart;
import com.codekat.joyprotein.domain.Member;
import com.codekat.joyprotein.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping(value="/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberDTO());
        return "members/createMember";
    }

    @PostMapping(value="/members/new")
    public String create(MemberDTO memberDTO) {
        Member member = new Member();
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        Address addr = new Address(memberDTO.getStreet(), memberDTO.getZipcode(),memberDTO.getDetail());
        member.setAddress(addr);
        memberService.join(member);
        member.setCart(new Cart());
        System.out.println("saved member id" + member.getId());
        return "redirect:/";
    }
}
