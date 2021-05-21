package com.example.optics.controllers;


import com.example.optics.services.HistoryOfOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Класс-контроллер для истории покупок пользователя
 */
@RestController
public class HistoryOfOrdersController {

    @Autowired
    private HistoryOfOrdersService historyOfOrdersService;

    /**
     * POST-запрос для добавления купленных товаров в БД
     * @param principal
     */
    @PostMapping("/addInHistory")
    public void addBasketHistory(Principal principal) {
        historyOfOrdersService.addBasketToHistory(principal);
    }


}
