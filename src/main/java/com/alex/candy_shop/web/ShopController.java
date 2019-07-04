package com.alex.candy_shop.web;

import com.alex.candy_shop.entities.Order;
import com.alex.candy_shop.entities.Product;
import com.alex.candy_shop.repos.OrderRepo;
import com.alex.candy_shop.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {
    private ProductRepo productRepo;
    private OrderRepo orderRepo;

    @Autowired
    public ShopController(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute
    public void addProductsToModel(Model model) {
        List<Product> list = (List<Product>) productRepo.findAll();
        model.addAttribute("products", list);
    }

    @GetMapping
    public String showOrderForm() {
        return "shop";
    }

    @PostMapping
    public String showCart() {
        return "cart";
    }
}
