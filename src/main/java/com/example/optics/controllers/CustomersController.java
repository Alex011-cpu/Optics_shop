package com.example.optics.controllers;


import com.example.optics.services.BasketService;
import com.example.optics.services.HistoryOfOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Класс-контроллер для страницы личного кабинета и др.
 */
@Controller
public class CustomersController {


    /*@Autowired
    private UserService userService;*/
    @Autowired
    private BasketService basketService;
    @Autowired
    private HistoryOfOrdersService historyOfOrdersService;

    /**
     *
     * @param principal
     * @return наименование html страницы String
     */
    @GetMapping("/")
    public String redirectToCustomer(Principal principal) {
        if (principal!= null) return "redirect:/customer";
        return "index";
    }

    /**
     * GET-запрос для страницы после входа в систему
     * @param principal
     * @param model
     * @return наименование html страницы String
     */
    @GetMapping("/customer")
    public String getSuccessPage(Principal principal, Model model) {
        model.addAttribute("BasketCount",basketService.countOfOrder());
        /*model.addAttribute("UserObj",userService.findUserByEmail(principal.getName()));*/
        return "customer_index";
    }

    /**
     * GET-запрос для страницы личного кабинета пользователя
     * @param principal
     * @param model
     * @return наименование html страницы String
     */
    @GetMapping("customer/info")
    public String getCustomerInfo(Principal principal, Model model) {
        model.addAttribute("BasketCount",basketService.countOfOrder());
        /*model.addAttribute("UserObj",userService.findUserByEmail(principal.getName()));*/
        /*model.addAttribute("HistoryList",historyOfOrdersService.findAllByUserId(userService
                .findUserByEmail(principal.getName()).getId()));*/
        return "personal_page";
    }

}
