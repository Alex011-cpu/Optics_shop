package com.example.optics.controllers;


import com.example.optics.models.Basket;
import com.example.optics.models.Product;
import com.example.optics.services.BasketService;
import com.example.optics.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Класс-контроллер для отображения страниц с товарами
 */
@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class ProductPageController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BasketService basketService;


    /**
     * GET-запрос для получения страницы с товарами
     * @param categoryName
     * @return наименование html страницы String
     */
    @GetMapping("/{categoryName}")
    public List<Product> getProductPage(@PathVariable String categoryName) {

        switch (categoryName) {
            case "healthGlasses":
                return productService.allProductsByCategory("Медицинские очки");
            case "sunglasses":
                return productService.allProductsByCategory("Солнцезащитные очки");
            case "contactLenses":
                return productService.allProductsByCategory("Контактные линзы");
        }
            return null;
    }

    @GetMapping("/count/{categoryName}")
    public int getCountByCategory(@PathVariable String categoryName) {

        switch (categoryName) {
            case "healthGlasses":
                return  productService.allProductsByCategory("Медицинские очки").size();
            case "sunglasses":
                return productService.allProductsByCategory("Солнцезащитные очки").size();
            case "contactLenses":
                return productService.allProductsByCategory("Контактные линзы").size();
        }
        return 0;
    }

    /**
     * GET-запрос для получения страницы с отсортированными товарами
     * @param categoryName
     * @param sortMethod
     * @return наименование html страницы String
     */
    @GetMapping("/{categoryName}/{sortMethod}")
    public List<Product> getProductPageWithSortPriceDesc(@PathVariable String categoryName,
                                                  @PathVariable String sortMethod) {
        switch (sortMethod) {
            case "sort-price-desc":
                if (categoryName.equals("healthGlasses")) {
                    return productService.allProductsByCategoryAndSortedByPriceDesc("Медицинские очки");
                } else if (categoryName.equals("sunglasses")) {
                    return productService.allProductsByCategoryAndSortedByPriceDesc("Солнцезащитные очки");
                } else if (categoryName.equals("contactLenses")) {
                    return productService.allProductsByCategoryAndSortedByPriceDesc("Контактные линзы");
                }
                break;
            case "sort-price-asc":
                if (categoryName.equals("healthGlasses")) {
                    return productService.allProductsByCategoryAndSortedByPriceAsc("Медицинские очки");
                } else if (categoryName.equals("sunglasses")) {
                    return productService.allProductsByCategoryAndSortedByPriceAsc("Солнцезащитные очки");
                } else if (categoryName.equals("contactLenses")) {
                    return productService.allProductsByCategoryAndSortedByPriceAsc("Контактные линзы");
                }
                break;
            case "sort-brand-desc":
                if (categoryName.equals("healthGlasses")) {
                    return productService.allProductsByCategoryAndSortedByBrandDesc("Медицинские очки");
                } else if (categoryName.equals("sunglasses")) {
                    return productService.allProductsByCategoryAndSortedByBrandDesc("Солнцезащитные очки");
                } else if (categoryName.equals("contactLenses")) {
                    return productService.allProductsByCategoryAndSortedByBrandDesc("Контактные линзы");
                }
                break;
            case "sort-brand-asc":
                if (categoryName.equals("healthGlasses")) {
                    return productService.allProductsByCategoryAndSortedByBrandAsc("Медицинские очки");
                } else if (categoryName.equals("sunglasses")) {
                    return productService.allProductsByCategoryAndSortedByBrandAsc("Солнцезащитные очки");
                } else if (categoryName.equals("contactLenses")) {
                    return productService.allProductsByCategoryAndSortedByBrandAsc("Контактные линзы");
                }
                break;
        }
        return null;
    }

}
