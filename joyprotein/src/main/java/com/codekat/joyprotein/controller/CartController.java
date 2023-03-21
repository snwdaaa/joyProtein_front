package com.codekat.joyprotein.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.codekat.joyprotein.domain.*;
import com.codekat.joyprotein.domain.items.Protein;
import com.codekat.joyprotein.service.ItemService;
import com.codekat.joyprotein.service.MemberService;
import com.codekat.joyprotein.service.OrderItemService;
import com.codekat.joyprotein.service.OrderService;

@Controller
@SessionAttributes("memberId")
public class CartController {
    @Autowired private MemberService memberService;
    @Autowired private ItemService itemService;
    @Autowired private OrderService orderService;
    @Autowired private OrderItemService orderItemService;
    
    @GetMapping("/cart")
    public String showCart(Model model, @ModelAttribute("memberId") Long memberId) throws Exception {
        System.out.println("received memberId = "+memberId);
        Member member = memberService.findOne(memberId);
        System.out.println("sql memberId = " + member.getId());
        List<CartItemDTO> cartItems = member.getCart().getOrderItems().stream()
        .map(orderItem -> new CartItemDTO(orderItem.getId(),orderItem.getItem().getName(),orderItem.getItem().getImgUrl(), orderItem.getItem().getPrice(), orderItem.getQuantity()))
        .collect(Collectors.toList());
        model.addAttribute("items", cartItems);
        return "cart";
    }

    @GetMapping("/cart/order")
    public String orderFromCart(@ModelAttribute("memberId") Long memberId,Model model) throws Exception{
        Long orderId = orderService.orderFromCart(memberId);
        return "redirect:/cart";
    }

    @PostMapping("/cart/add")
    public String addProteinToCart(ProteinOrderDTO proteinDTO) throws Exception{
        Protein item = (Protein) itemService.findOne(proteinDTO.getId());
        orderItemService.addItemToCart(1L, item, proteinDTO.getQuantity());
        return "redirect:/cart";
    }
}
