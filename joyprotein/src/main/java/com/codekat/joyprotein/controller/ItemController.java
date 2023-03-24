package com.codekat.joyprotein.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.codekat.joyprotein.domain.items.Protein;
import com.codekat.joyprotein.service.ItemService;
import com.codekat.joyprotein.service.OrderService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@SessionAttributes("memberId")
public class ItemController {
    @Autowired private ItemService itemService;
    @Autowired private OrderService orderService;

    @GetMapping(value="/protein/buy")
    public String getMethodName(@RequestParam Long id, Model model) {
        Protein protein = (Protein) itemService.findOne(id);
        model.addAttribute("protein", protein);
        return "items/proteinBuy";
    }

    /**
     * un do !!!!!!!
     * @param proteinDTO
     * @return
     * @throws Exception
     */
    @PostMapping(value="/protein/order")
    public String orderProtein(ProteinOrderDTO proteinDTO) throws Exception {
    // Protein item = (Protein) itemService.findOne(proteinDTO.getId());
    orderService.orderItem(1L, proteinDTO.getId(), proteinDTO.getQuantity());
    return "redirect:/";
    }

}
    

