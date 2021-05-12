package com.example.optics.controllers;


import com.example.optics.services.HistoryOfOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HistoryOfOrdersController {

    @Autowired
    private HistoryOfOrdersService historyOfOrdersService;

    @PostMapping("/addInHistory")
    public void addBasketHistory(Principal principal) {
        historyOfOrdersService.addBasketToHistory(principal);
    }


}
