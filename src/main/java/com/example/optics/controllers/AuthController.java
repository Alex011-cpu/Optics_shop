package com.example.optics.controllers;


import com.example.optics.models.User;
import com.example.optics.repository.UserRepository;
import com.example.optics.services.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class AuthController {

    @Autowired
    private BasketService basketService;

    @GetMapping("/auth/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/auth/logout")
    public String logoutFromAc(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            basketService.deleteAll();
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/";
    }

}
