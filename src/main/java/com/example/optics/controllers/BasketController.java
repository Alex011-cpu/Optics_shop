package com.example.optics.controllers;


import com.example.optics.models.Basket;
import com.example.optics.services.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Класс-контроллер для корзины с товарами
 */
@RestController
public class BasketController {

    @Autowired
    private BasketService basketService;

    /*@Autowired
    private UserService userService;*/


    @ResponseBody
    @PostMapping("/product/addInBasket")
    public Basket addToBasket(@RequestBody Basket basket) {
        basketService.addProduct(basket);
        return basket;
    }


    @ResponseBody
    @DeleteMapping("/product/delete")
    public Basket deleteFromBasket(@RequestBody Basket basket) {
        basketService.delProduct(basket);
        return basket;
    }


    @GetMapping("/cartProducts")
    public List<Basket> getBasketPage(Model model, Principal principal) {
        /*model.addAttribute("BasketCount", basketService.countOfOrder());
        if (principal == null) model.addAttribute("UserObj", null);
        else {
            model.addAttribute("UserObj", userService.loadUserByUsername(principal.getName()));
            model.addAttribute("BasketList",basketService.findAll());
            model.addAttribute("SumOfOrder",basketService.sumOfOrder());
        }
        return "shopping_cart";*/

        return basketService.findAll();
    }
}
