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

    @OneToOne(cascade = CascadeType.ALL)
    private OrderDetails orderDetails = new OrderDetails();

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
