package com.alex.candy_shop.entities;

import com.alex.candy_shop.util.Utils;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // still not shown in DB
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_details_id")
    private OrderDetails orderDetails;

    public Order() {
        orderDetails = new OrderDetails();
        orderDetails.setOrder(this);
    }

    @PrePersist
    void createdAt() {
        this.dateTime = LocalDateTime.now();
    }

    // Two digits (cents) after the decimal point format
    public String getSumForDisplay() {
        return Utils.moneyToDisplay(sum);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", sum=" + sum +
                ", user=" + user +
                '}';
    }
}
