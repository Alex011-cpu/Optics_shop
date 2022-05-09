package com.example.optics.services;

import com.example.optics.models.Basket;
import com.example.optics.models.Product;
import com.example.optics.repository.BasketRepository;
import com.example.optics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс-сервис для корзины
 */
@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private ProductService productService;
    /**
     * Метод для добавления товара в корзину с учетом ограничения( не более 9 товаров)
     * @param product
     * @return boolean
     */
    public boolean addProduct(Product product) {


        if (basketRepository.findByProduct_Id(product.getId()).isPresent()) {
            Basket basketFromDb = basketRepository.findByProduct_Id(product.getId()).get();
            if (basketFromDb.getCount() >= 1 && countOfOrder()<9) {
                basketFromDb.setProduct(product);
                basketFromDb.setCount(basketFromDb.getCount() + 1);
                basketFromDb.setSum(product.getPrice() * basketFromDb.getCount());
                basketRepository.save(basketFromDb);
                return true;
            }
        }
        else if (countOfOrder() < 9) {
            Basket newBasket = new Basket();
            newBasket.setProduct(product);
            newBasket.setCount(1);
            newBasket.setSum(product.getPrice());
            basketRepository.save(newBasket);
            return true;
        }
        return false;
    }

    /**
     * Метод для удаления товара из корзины
     * @param productId
     * @return boolean
     */
    public String delProduct(Long productId) {

        if (basketRepository.findByProduct_Id(productId).isPresent()) {
            Basket basketFromDb = basketRepository.findByProduct_Id(productId).get();
            Product productFromDb = productService.findProductById(productId);
            if (basketFromDb.getCount() > 1) {
                basketFromDb.setProduct(productFromDb);
                basketFromDb.setCount(basketFromDb.getCount() - 1);
                basketFromDb.setSum(basketFromDb.getSum() - productFromDb.getPrice());
                basketRepository.save(basketFromDb);
            } else if (basketFromDb.getCount() == 1) {
                basketRepository.delete(basketFromDb);
            }
        }
        return "success";
    }

    public List<Basket> findAll() {
        return basketRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public List<Product> findAllProductInBasket() {
        return basketRepository.findAll().stream().map(Basket::getProduct).collect(Collectors.toList());
    }

    public Basket findBasketById(Long id) {
        return basketRepository.findByProduct_Id(id).orElse(null);
    }

    public int countOfOrder() {
        return basketRepository.allCount().orElse(0);
    }

    public int sumOfOrder() {
        return basketRepository.sum().orElse(0);
    }

    public boolean deleteAll() {
        basketRepository.deleteAll();
        return true;
    }
}
