package com.alex.candy_shop.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double sum;
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "user_id")
    private User user;
/*
    @ManyToMany(targetEntity = Product.class)
    private List<Product> products = new ArrayList<>();
*/
    @ManyToMany(targetEntity = Product.class) // use this transitional but should be done through product id
    private List<Integer> quantities = new ArrayList<>();

    @PrePersist
    void createdAt() {
        this.dateTime = LocalDateTime.now();
    }
/*
    void calculateSum() {
        for (int i = 0; i < products.size(); i++) {
            sum += products.get(i).getPrice() * quantities.get(i);
        }
    } */

}
