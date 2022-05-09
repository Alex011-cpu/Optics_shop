package com.example.optics.controllers;


import com.example.optics.models.Basket;
import com.example.optics.models.Product;
import com.example.optics.models.User;
import com.example.optics.services.BasketService;
import com.example.optics.services.HistoryOfOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Класс-контроллер для корзины с товарами
 */
@RestController
@RequestMapping("/basket")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class BasketController {

    @Autowired
    private BasketService basketService;


    @ResponseBody
    @PostMapping("/addInBasket")
    public String addToBasket(@RequestBody Product product) {
        if (basketService.addProduct(product)) return "success";
        return "error";
    }



    @DeleteMapping("/delete/{productId}")
    public String deleteFromBasket(@PathVariable Long productId) {
        return basketService.delProduct(productId);
    }

    @DeleteMapping("/deleteAll")
    public boolean deleteAll() {
        return basketService.deleteAll();
    }

    @GetMapping("/countProducts")
    public int getCountOfProductInBasket () {
        return basketService.countOfOrder();
    }

    @GetMapping("/sumOfOrder")
    public int getSumOfOrder() {
        int data = basketService.sumOfOrder();
        return data;
    }

    @GetMapping("/product/{productId}")
    public Basket getBasketByProductId(@PathVariable Long productId) {
        return basketService.findBasketById(productId);
    }

    @GetMapping("/cartProducts")
    public List<Basket> getBasketPage() {
        return basketService.findAll();
    }

}
