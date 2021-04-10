package com.example.optics.controllers;


import com.example.optics.models.Role;
import com.example.optics.models.User;
import com.example.optics.security.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserDetailServiceImpl userDetailService;

    @Autowired
    public RegistrationController(UserDetailServiceImpl userDetailService) {
        this.userDetailService = userDetailService;
    }

    @GetMapping
    public String registration(Model model) {
        model.addAttribute("UserForm",new User());
        return "registration";
    }

    @PostMapping
    public String addUser(@ModelAttribute("UserForm") User userForm, BindingResult bindingResult) {
        userForm.setRole(Role.USER);
        userDetailService.saveUser(userForm);
        return "redirect:/";
    }
}
