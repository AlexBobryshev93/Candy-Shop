package com.alex.candy_shop.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_details_id")
    private OrderDetails orderDetails;

    @OneToOne
    private Product product;

    private int quantity;

}
