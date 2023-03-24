package com.codekat.joyprotein.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.codekat.joyprotein.domain.Member;
import com.codekat.joyprotein.domain.items.Item;
import com.codekat.joyprotein.service.ItemService;
import com.codekat.joyprotein.service.MemberService;


@Controller
// @RequiredArgsConstructor
@SessionAttributes("memberId")
public class HomeController {
    @Autowired private MemberService memberService;
    @Autowired private ItemService itemService;


    @GetMapping("/")
    public String home() {
        return "home";
    }
    @GetMapping("/protein")
    public String protein(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/protein";
    }

    @GetMapping("/login")
    public String showCart(@RequestParam("id") Long memberId, Model model){
        model.addAttribute("memberId", memberId);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("memberId");
        return "redirect:/";
    }

    @GetMapping(value="/orders")
    public String viewOrders(@ModelAttribute("memberId") Long memberId,Model model) {
        Member member = memberService.findOne(memberId);
        model.addAttribute("orders",member.getOrders());
        return "orders";
    }
    
    
}