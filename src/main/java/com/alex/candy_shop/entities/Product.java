package com.alex.candy_shop.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private double price;
    private int inStock;

    public Product() {
    }

    public Product(String name, double price, int inStock) {
        this.name = name;
        this.price = price;
        this.inStock = inStock;
    }

    // Two digits (cents) after the decimal point
    public double getPriceForDisplay() {
        return Math.round(price * 100) / 100d;
    }
}
