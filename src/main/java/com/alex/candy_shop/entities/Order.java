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

    @ManyToMany(targetEntity = Product.class)
    private Set<Product> products = new HashSet<>();

    @ManyToMany(targetEntity = Product.class) // how to merge 2 transitional tables?
    private Set<Integer> quantities = new HashSet<>();

    @PrePersist
    void createdAt() {
        this.dateTime = LocalDateTime.now();
    }

}
