package com.alex.candy_shop.web;

import com.alex.candy_shop.entities.Order;
import com.alex.candy_shop.entities.OrderItem;
import com.alex.candy_shop.entities.Product;
import com.alex.candy_shop.repos.OrderRepo;
import com.alex.candy_shop.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
    private Order orderToPurchase;

    @Autowired
    public ShopController(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        Order order = new Order();
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
        orderToPurchase.getOrderDetails().getOrderItems()
                .forEach(orderItem -> orderItem.getProduct()
                        .setInStock(orderItem.getProduct().getInStock() - orderItem.getQuantity()));

        //... money substraction
        orderToPurchase.getOrderDetails().getOrderItems().forEach(orderItem -> productRepo.save(orderItem.getProduct()));
        orderRepo.save(orderToPurchase);
        return "thanks";
    }
}
