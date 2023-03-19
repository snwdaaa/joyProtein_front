package com.codekat.joyprotein.controller;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.codekat.joyprotein.domain.Member;
import com.codekat.joyprotein.domain.items.Item;
import com.codekat.joyprotein.domain.items.Protein;
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
        return "protein";
    }

    @GetMapping("/login")
    public String showCart(@RequestParam("id") Long memberId, Model model){
        model.addAttribute("memberId", memberId);
        return "redirect:/";
    }


    @GetMapping("/cart")
    public String showCart(Model model, @ModelAttribute("memberId") Long memberId) throws Exception {
        System.out.println("received memberId = "+memberId);
        Member member = new Member();
        member = memberService.findOne(memberId);
        System.out.println("sql memberId = " + member.getId());
        List<CartItemDTO> cartItems = member.getCart().getOrderItems().stream()
        .map(orderItem -> new CartItemDTO(orderItem.getId(),orderItem.getItem().getName(),orderItem.getItem().getImgUrl(), orderItem.getItem().getPrice(), orderItem.getQuantity()))
        .collect(Collectors.toList());
        model.addAttribute("items", cartItems);
        return "cart";
    }
    
}