package com.example.optics.controllers;


import com.example.optics.models.User;
import com.example.optics.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Класс-контроллер для регистрации
 */

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class RegistrationController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * POST-запрос на добавление пользователя в БД
     * @param user
     * @return наименование html страницы String
     */
    @PostMapping("/api/auth/registration")
    public String addUser(@RequestBody User user) {

        if (!userDetailsService.saveUser(user)){
            return "Пользователь с такой почтой уже существует";
        }
        return "success";
    }

}
