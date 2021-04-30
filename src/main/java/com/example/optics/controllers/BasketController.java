package com.example.optics.controllers;


import com.example.optics.models.Basket;
import com.example.optics.services.BasketService;
import com.example.optics.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class BasketController {

    @Autowired
    private BasketService basketService;

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/product/healthGlasses")
    public void addToBasket(@RequestBody Basket basket) {
        basketService.addProduct(basket);
    }

    @GetMapping("/cartProducts")
    public String getBasketPage(Model model, Principal principal) {
        model.addAttribute("BasketCount", basketService.countOfOrder());
        if (principal == null) model.addAttribute("UserObj", null);
        else {
            model.addAttribute("UserObj", userService.loadUserByUsername(principal.getName()));
            model.addAttribute("BasketList",basketService.findAll());
        }
        return "shopping_cart";
    }
}
