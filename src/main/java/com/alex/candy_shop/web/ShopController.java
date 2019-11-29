package com.alex.candy_shop.web;

import com.alex.candy_shop.entities.Order;
import com.alex.candy_shop.entities.OrderItem;
import com.alex.candy_shop.entities.Product;
import com.alex.candy_shop.repos.OrderRepo;
import com.alex.candy_shop.repos.ProductRepo;
import com.alex.candy_shop.repos.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    private UserRepo userRepo;

    private Order orderToPurchase;

    public ShopController(ProductRepo productRepo, OrderRepo orderRepo, UserRepo userRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        Order order = new Order();
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        order.setUser(userRepo.findByUsername(principal.getUsername()));

        List<Product> list = (List<Product>) productRepo.findAll();
        for (Product product : list) {
            order.getOrderDetails().getOrderItems().add(new OrderItem(order.getOrderDetails(), product, 0));
        }

        return order;
    }

    @GetMapping
    public String showOrderForm() {
        return "shop";
    }

    @PostMapping
    public String showCart(@ModelAttribute Order order) {
        order.getOrderDetails().getOrderItems().removeIf(p -> p.getQuantity() == 0); // filter the products with zero quantities
        order.getOrderDetails().calculateSum();
        orderToPurchase = order;
        return "cart";
    }

    @GetMapping("/purchase")
    @Transactional
    public String purchase() { //still the issue with zero order, try validation
        // ... if not enough money

        // products in stock subtraction
        orderToPurchase.getOrderDetails().getOrderItems()
                .forEach(orderItem -> orderItem.getProduct()
                        .setInStock(orderItem.getProduct().getInStock() - orderItem.getQuantity()));

        System.out.println(orderToPurchase.getSum());
        // money subtraction
        orderToPurchase.getUser().setMoneyBalance(orderToPurchase.getUser().getMoneyBalance() -
                orderToPurchase.getSum());

        // saving to the DB
        orderToPurchase.getOrderDetails().getOrderItems().forEach(orderItem -> productRepo.save(orderItem.getProduct()));
        orderRepo.save(orderToPurchase);
        userRepo.save(orderToPurchase.getUser());
        return "thanks";
    }
}
