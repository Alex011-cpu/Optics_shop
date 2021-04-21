package com.example.optics.controllers;


import com.example.optics.services.ProductService;
import com.example.optics.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/product")
public class ProductPageController {

    @Autowired
    private ProductService productService;
    @Autowired
    UserService userService;
    @GetMapping("healthGlasses")
    public String getHealthGlassesPage(Model model, Principal principal) {
        model.addAttribute("UserObj",userService.loadUserByUsername(principal.getName()));
        model.addAttribute("productsList", productService.allProductsByCategory("Медицинские очки"));
        return "health_glasses";
    }
}
