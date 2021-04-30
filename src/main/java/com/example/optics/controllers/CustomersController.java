package com.example.optics.controllers;


import com.example.optics.models.Customer;
import com.example.optics.repository.UserRepository;
import com.example.optics.services.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/customer")
public class CustomersController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BasketService basketService;

    @GetMapping
    public String getSuccessPage(Principal principal, Model model) {
        model.addAttribute("BasketCount",basketService.countOfOrder());
        model.addAttribute("UserObj",userRepository.findByEmail(principal.getName()));
        return "customer_index";
    }
    @GetMapping("/info")
    public String getCustomerInfo(Principal principal, Model model) {
        model.addAttribute("BasketCount",basketService.countOfOrder());
        model.addAttribute("UserObj",userRepository.findByEmail(principal.getName()));
        return "personal_page";
    }

}
