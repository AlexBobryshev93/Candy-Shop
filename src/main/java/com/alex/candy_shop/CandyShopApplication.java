package com.alex.candy_shop;

import com.alex.candy_shop.entities.Product;
import com.alex.candy_shop.entities.User;
import com.alex.candy_shop.repos.ProductRepo;
import com.alex.candy_shop.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class CandyShopApplication {
    @Autowired
    PasswordEncoder encoder;

    @Bean
    public CommandLineRunner dataLoader(UserRepo userRepo, ProductRepo productRepo) {
        return args -> {
            // adds a new User if the DB still doesn't contain at least one
            if (((List) userRepo.findAll()).isEmpty()) {
                User user = new User();
                user.setUsername("user1");
                user.setPassword(encoder.encode("pass"));
                user.setMoneyBalance(200);
                userRepo.save(user);
            }

            // adds Product items if the DB still doesn't contain at least one
            if (((List) productRepo.findAll()).isEmpty()) {
                Product sneakers = new Product("sneakers", 1.1, 10);
                Product twins = new Product("twins", 0.75, 0);
                Product kidcut = new Product("kidcut", 0.8, 5);
                Product bouncy = new Product("bouncy", 0.9, 59);
                Product earth = new Product("earth", 0.95, 7);

                productRepo.save(sneakers);
                productRepo.save(twins);
                productRepo.save(kidcut);
                productRepo.save(bouncy);
                productRepo.save(earth);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(CandyShopApplication.class, args);
    }
}
