package com.alex.candy_shop;

import com.alex.candy_shop.entities.Product;
import com.alex.candy_shop.repos.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CandyShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(CandyShopApplication.class, args);
    }
/*
    @Bean
    public CommandLineRunner dataLoader(ProductRepo repo) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                repo.save(new Product("sneakers", 1.1, 10));
                repo.save(new Product("twins", 0.75, 0));
                repo.save(new Product("kidcut", 0.8, 5));
                repo.save(new Product("bouncy", 0.9, 59));
                repo.save(new Product("earth", 0.95, 7));
            }
        };
    } */
}
