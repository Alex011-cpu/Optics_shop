package com.example.optics.controllers;


import com.example.optics.models.Basket;
import com.example.optics.services.BasketService;
import com.example.optics.services.ProductService;
import com.example.optics.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/product")
public class ProductPageController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private BasketService basketService;

    @GetMapping("/healthGlasses")
    public String getHealthGlassesPage(Model model, Principal principal) {
        model.addAttribute("BasketCount", basketService.countOfOrder());
        if (principal == null) model.addAttribute("UserObj", null);
        else {
            model.addAttribute("UserObj", userService.loadUserByUsername(principal.getName()));
        }
            model.addAttribute("BasketForm",new Basket());
            model.addAttribute("productsList", productService.allProductsByCategory("Медицинские очки"));
        return "health_glasses";
    }

    @GetMapping("/sunglasses")
    public String getSunglassesPage(Model model, Principal principal) {
        model.addAttribute("BasketCount", basketService.countOfOrder());
        if (principal == null) model.addAttribute("UserObj", null);
        else {
            model.addAttribute("UserObj", userService.loadUserByUsername(principal.getName()));
        }
        model.addAttribute("BasketForm",new Basket());
        model.addAttribute("productsList", productService.allProductsByCategory("Солнцезащитные очки"));
        return "sunglasses";
    }
}
