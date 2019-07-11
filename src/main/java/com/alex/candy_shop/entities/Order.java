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
    @JoinColumn(name = "order_details_id")
    private OrderDetails details;

    @PrePersist
    void createdAt() {
        this.dateTime = LocalDateTime.now();
    }

    public Order() {
        details = new OrderDetails();
        details.setId(id);
    }
/*
    void calculateSum() {
        for (int i = 0; i < products.size(); i++) {
            sum += products.get(i).getPrice() * quantities.get(i);
        }
    } */

}
