package com.example.optics.controllers;


import com.example.optics.models.Basket;
import com.example.optics.services.BasketService;
import com.example.optics.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Класс-контроллер для отображения страниц с товарами
 */
@Controller
@RequestMapping("/product")
public class ProductPageController {

    @Autowired
    private ProductService productService;
    /*@Autowired
    private UserService userService;*/
    @Autowired
    private BasketService basketService;

    /**
     * Метод для проверки: зашел пользователь в систему или нет
     * @param model
     * @param principal
     * @param category
     */
    public void checkUserAndSetModel(Model model, Principal principal, String category) {
        model.addAttribute("BasketCount", basketService.countOfOrder());
        if (principal == null) model.addAttribute("UserObj", null);
        else {
           /*model.addAttribute("UserObj", userService.loadUserByUsername(principal.getName()));*/
        }
        model.addAttribute("BasketForm",new Basket());
        model.addAttribute("CountByCategory", productService.allProductsByCategory(category).size());
    }

    /**
     * GET-запрос для получения страницы с товарами
     * @param model
     * @param principal
     * @param categoryName
     * @return наименование html страницы String
     */
    @GetMapping("/{categoryName}")
    public String getProductPage(Model model, Principal principal, @PathVariable String categoryName) {
            if (categoryName.equals("healthGlasses")) {
            checkUserAndSetModel(model, principal, "Медицинские очки");
            model.addAttribute("productsList", productService.allProductsByCategory("Медицинские очки"));
            return "health_glasses";
            } else if (categoryName.equals("sunglasses")) {
                checkUserAndSetModel(model, principal,"Солнцезащитные очки");
                model.addAttribute("productsList", productService.allProductsByCategory("Солнцезащитные очки"));
                return "sunglasses";
            } else if (categoryName.equals("contactLenses")) {
                checkUserAndSetModel(model, principal,"Контактные линзы");
                model.addAttribute("productsList", productService.allProductsByCategory("Контактные линзы"));
                return "contact_lenses";
            }
            return "";
    }

    /**
     * GET-запрос для получения страницы с отсортированными товарами
     * @param model
     * @param principal
     * @param categoryName
     * @param sortMethod
     * @return наименование html страницы String
     */
    @GetMapping("/{categoryName}/{sortMethod}")
    public String getProductPageWithSortPriceDesc(Model model, Principal principal, @PathVariable String categoryName,
                                                  @PathVariable String sortMethod) {
        switch (sortMethod) {
            case "sort-price-desc":
                if (categoryName.equals("healthGlasses")) {
                    checkUserAndSetModel(model, principal, "Медицинские очки");
                    model.addAttribute("productsList", productService
                            .allProductsByCategoryAndSortedByPriceDesc("Медицинские очки"));
                    return "health_glasses";
                } else if (categoryName.equals("sunglasses")) {
                    checkUserAndSetModel(model, principal, "Солнцезащитные очки");
                    model.addAttribute("productsList", productService
                            .allProductsByCategoryAndSortedByPriceDesc("Солнцезащитные очки"));
                    return "sunglasses";
                } else if (categoryName.equals("contactLenses")) {
                    checkUserAndSetModel(model, principal, "Контактные линзы");
                    model.addAttribute("productsList", productService
                            .allProductsByCategoryAndSortedByPriceDesc("Контактные линзы"));
                    return "contact_lenses";
                }
                break;
            case "sort-price-asc":
                if (categoryName.equals("healthGlasses")) {
                    checkUserAndSetModel(model, principal, "Медицинские очки");
                    model.addAttribute("productsList", productService
                            .allProductsByCategoryAndSortedByPriceAsc("Медицинские очки"));
                    return "health_glasses";
                } else if (categoryName.equals("sunglasses")) {
                    checkUserAndSetModel(model, principal, "Солнцезащитные очки");
                    model.addAttribute("productsList", productService
                            .allProductsByCategoryAndSortedByPriceAsc("Солнцезащитные очки"));
                    return "sunglasses";
                } else if (categoryName.equals("contactLenses")) {
                    checkUserAndSetModel(model, principal, "Контактные линзы");
                    model.addAttribute("productsList", productService
                            .allProductsByCategoryAndSortedByPriceAsc("Контактные линзы"));
                    return "contact_lenses";
                }
                break;
            case "sort-brand-desc":
                if (categoryName.equals("healthGlasses")) {
                    checkUserAndSetModel(model, principal, "Медицинские очки");
                    model.addAttribute("productsList", productService
                            .allProductsByCategoryAndSortedByBrandDesc("Медицинские очки"));
                    return "health_glasses";
                } else if (categoryName.equals("sunglasses")) {
                    checkUserAndSetModel(model, principal, "Солнцезащитные очки");
                    model.addAttribute("productsList", productService
                            .allProductsByCategoryAndSortedByBrandDesc("Солнцезащитные очки"));
                    return "sunglasses";
                } else if (categoryName.equals("contactLenses")) {
                    checkUserAndSetModel(model, principal, "Контактные линзы");
                    model.addAttribute("productsList", productService
                            .allProductsByCategoryAndSortedByBrandDesc("Контактные линзы"));
                    return "contact_lenses";
                }
                break;
            case "sort-brand-asc":
                if (categoryName.equals("healthGlasses")) {
                    checkUserAndSetModel(model, principal, "Медицинские очки");
                    model.addAttribute("productsList", productService
                            .allProductsByCategoryAndSortedByBrandAsc("Медицинские очки"));
                    return "health_glasses";
                } else if (categoryName.equals("sunglasses")) {
                    checkUserAndSetModel(model, principal, "Солнцезащитные очки");
                    model.addAttribute("productsList", productService
                            .allProductsByCategoryAndSortedByBrandAsc("Солнцезащитные очки"));
                    return "sunglasses";
                } else if (categoryName.equals("contactLenses")) {
                    checkUserAndSetModel(model, principal, "Контактные линзы");
                    model.addAttribute("productsList", productService
                            .allProductsByCategoryAndSortedByBrandAsc("Контактные линзы"));
                    return "contact_lenses";
                }
                break;
        }
        return "";
    }



}
