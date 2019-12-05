package com.alex.candy_shop.web;

import com.alex.candy_shop.repos.OrderRepo;
import com.alex.candy_shop.repos.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/my_orders")
@SessionAttributes("user")
public class AccountController {
    private OrderRepo orderRepo;
    private UserRepo userRepo;

    public AccountController(OrderRepo orderRepo, UserRepo userRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
    }

    @ModelAttribute
    public void addOrdersToModel(Model model) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", userRepo.findByUsername(principal.getUsername()));
        model.addAttribute("orders", orderRepo.findAllByUser(userRepo.findByUsername(principal.getUsername())));
    }

    @GetMapping
    public String showOrderList() {
        return "my_orders";
    }
}
