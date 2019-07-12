package com.alex.candy_shop;

import com.alex.candy_shop.entities.Order;
import com.alex.candy_shop.entities.OrderItem;
import com.alex.candy_shop.entities.Product;
import com.alex.candy_shop.repos.OrderRepo;
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

    @Bean
    public CommandLineRunner dataLoader(ProductRepo productRepo, OrderRepo orderRepo) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                /*
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


                Order order1 = new Order();
                OrderItem orderItem1 = new OrderItem();
                orderItem1.setProduct(productRepo.findFirstByName("sneakers"));
                orderItem1.setQuantity(3);
                order1.getDetails().getItems().add(orderItem1);
                orderRepo.save(order1);
*/
            }
        };
    }
}
