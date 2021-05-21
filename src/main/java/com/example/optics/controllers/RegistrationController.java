package com.example.optics.controllers;


import com.example.optics.models.User;
import com.example.optics.services.BasketService;
import com.example.optics.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Класс-контроллер для регистрации
 */
@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    /**
     * GET-запрос для отображения страницы регистрации
     * @param user
     * @return наименование html страницы String
     */
    @GetMapping({"/registration"})
    public String registration(@ModelAttribute("UserForm") User user) {
        return "registration";
    }

    /**
     * POST-запрос на добавление пользователя в БД
     * @param userForm
     * @param bindingResult
     * @param model
     * @param ra
     * @return наименование html страницы String
     */
    @PostMapping("/registration")
    public String addUser(@Valid @ModelAttribute("UserForm")  User userForm,
                          BindingResult bindingResult, Model model, RedirectAttributes ra) {

        if (bindingResult.hasErrors()) {

            return "registration";
        }

        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("errorMessage", "Пароли не совпадают");
            return "registration";
        }
        if (!userService.saveUser(userForm)){
            model.addAttribute("errorMessage", "Пользователь с такой почтой уже существует");
            return "registration";
        }
        return "redirect:/";
    }

}
