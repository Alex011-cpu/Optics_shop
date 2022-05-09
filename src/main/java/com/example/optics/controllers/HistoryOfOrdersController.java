package com.example.optics.controllers;


import com.example.optics.models.HistoryOfOrders;
import com.example.optics.models.User;
import com.example.optics.services.HistoryOfOrdersService;
import com.example.optics.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Класс-контроллер для истории покупок пользователя
 */
@RestController
@RequestMapping("/history")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class HistoryOfOrdersController {

    @Autowired
    private HistoryOfOrdersService historyOfOrdersService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;
    /**
     * POST-запрос для добавления купленных товаров в БД
     * @param user
     */
    @PostMapping("/addInHistory")
    public String addBasketHistory(@RequestBody User user) {
        historyOfOrdersService.addBasketToHistory(user);
        return "success";
    }

    @GetMapping("/orders")
    public List<HistoryOfOrders> getHistory () {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return  historyOfOrdersService.findAllByUserId(userDetailsService.getUserByUsername(username).getId());
    }


}
