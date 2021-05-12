package com.example.optics.controllers;


import com.example.optics.repository.UserRepository;
import com.example.optics.services.BasketService;
import com.example.optics.services.HistoryOfOrdersService;
import com.example.optics.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class CustomersController {


    @Autowired
    private UserService userService;
    @Autowired
    private BasketService basketService;
    @Autowired
    private HistoryOfOrdersService historyOfOrdersService;

    @GetMapping("/")
    public String redirectToCustomer(Principal principal) {
        if (principal!= null) return "redirect:/customer";
        return "index";
    }

    @GetMapping("/customer")
    public String getSuccessPage(Principal principal, Model model) {
        model.addAttribute("BasketCount",basketService.countOfOrder());
        model.addAttribute("UserObj",userService.findUserByEmail(principal.getName()));
        return "customer_index";
    }

    @GetMapping("customer/info")
    public String getCustomerInfo(Principal principal, Model model) {
        model.addAttribute("BasketCount",basketService.countOfOrder());
        model.addAttribute("UserObj",userService.findUserByEmail(principal.getName()));
        model.addAttribute("HistoryList",historyOfOrdersService.findAllByUserId(userService
                .findUserByEmail(principal.getName()).getId()));
        return "personal_page";
    }

}
