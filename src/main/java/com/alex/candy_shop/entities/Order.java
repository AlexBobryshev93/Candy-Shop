package com.alex.candy_shop.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private OrderDetails orderDetails = new OrderDetails();

    public Order() {
        orderDetails.setOrder(this);
    }

    @PrePersist
    void createdAt() {
        this.dateTime = LocalDateTime.now();
    }

/*
    @PrePersist
    void calculateSum() {
        for (int i = 0; i < products.size(); i++) {
            sum += products.get(i).getPrice() * quantities.get(i);
        }
    } */

}
