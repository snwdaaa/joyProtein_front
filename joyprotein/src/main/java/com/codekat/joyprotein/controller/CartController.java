package com.codekat.joyprotein.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.codekat.joyprotein.domain.*;
import com.codekat.joyprotein.domain.items.Item;
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
        Member member = memberService.findOne(memberId);
        List<CartItemDTO> cartItems = member.getCart().getOrderItems().stream()
        .map(orderItem -> new CartItemDTO(orderItem.getId(),orderItem.getName(),orderItem.getItem().getProduct().getImgUrl(), orderItem.getItem().getPrice(), orderItem.getQuantity()))
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
    public String addProteinToCart(@ModelAttribute("memberId") Long memberId, ProteinOrderDTO proteinDTO) throws Exception{
        System.out.println("start\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("protein weight = " + proteinDTO.getWeight() +", tastcode = "+proteinDTO.getTasteCode());
        Item item = itemService.takeProtein(proteinDTO.getId(), proteinDTO.getWeight(), proteinDTO.getTasteCode());
        OrderItem orderItem = orderItemService.addItemToCart(memberId, item, proteinDTO.getQuantity());
        // orderItem.setName(item.getProduct().getName()+ ") 맛: "+TasteRepository.getTaste(((Protein) item).getTasteCode()) + ", 용량: "+ ((Protein) item).getWeight() + "g");
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + orderItem.getName());
        return "redirect:/cart";
    }
}
