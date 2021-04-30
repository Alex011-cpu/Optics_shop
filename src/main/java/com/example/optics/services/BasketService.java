package com.example.optics.services;

import com.example.optics.models.Basket;
import com.example.optics.models.Product;
import com.example.optics.repository.BasketRepository;
import com.example.optics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private ProductService productService;

    public boolean addProduct(Basket basket) {
        if (basketRepository.findByProduct_Id(basket.getProduct().getId()).isPresent()) {
            Basket basketFromDb = basketRepository.findByProduct_Id(basket.getProduct().getId()).get();
            if (basketFromDb.getCount() >= 1 && countOfOrder()<9) {
                basketFromDb.setProduct(basket.getProduct());
                basketFromDb.setCount(basketFromDb.getCount() + 1);
                basketRepository.save(basketFromDb);
            }
        }else if (countOfOrder() > 9) {
            return false;
        }
        else if (countOfOrder() < 9) {
            basket.setCount(1);
            basketRepository.save(basket);
        }
        return true;
    }

    public List<Basket> findAll() {
        return basketRepository.findAll();
    }

    public List<Product> findAllProductInBasket() {
        return basketRepository.findAll().stream().map(Basket::getProduct).collect(Collectors.toList());
    }

    public Basket findBasketById(Long id) {
        return basketRepository.findByProduct_Id(id).orElse(null);
    }

    public int countOfOrder() {
        return basketRepository.sum().orElse(0);
    }

    public boolean deleteAll() {
        basketRepository.deleteAll();
        return true;
    }
}
