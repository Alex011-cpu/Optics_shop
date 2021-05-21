package com.example.optics.services;


import com.example.optics.models.Basket;
import com.example.optics.models.HistoryOfOrders;
import com.example.optics.repository.HistoryOrdersRepository;
import com.example.optics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Класс-сервис для истории покупок
 */
@Service
public class HistoryOfOrdersService {

    @Autowired
    private HistoryOrdersRepository historyOrdersRepository;
    @Autowired
    private BasketService basketService;
    @Autowired
    private UserService userService;

    /**
     * Метод для добавления купленных товаров в БД
     * @param principal
     * @return boolean
     */
    public boolean addBasketToHistory(Principal principal) {
        if (!basketService.findAll().isEmpty()) {
            System.out.println(basketService.findAll().size());
            List<HistoryOfOrders> historyOfOrdersList = new ArrayList<>();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            for (Basket basket : basketService.findAll()) {
                HistoryOfOrders historyOfOrders = new HistoryOfOrders();
                historyOfOrders.setUser(userService.findUserByEmail(principal.getName()));
                historyOfOrders.setProduct(basket.getProduct());
                historyOfOrders.setCount(basket.getCount());
                historyOfOrders.setSum(basket.getSum());
                historyOfOrders.setCreationDate(dateFormat.format(date));
                historyOfOrdersList.add(historyOfOrders);
            }
            historyOrdersRepository.saveAll(historyOfOrdersList);
            basketService.deleteAll();
            return true;
        } else {
            return false;
        }
    }

    public List<HistoryOfOrders> findAllByUserId(Long id) {
        return historyOrdersRepository.findAllByUserId(id);
    }

}
