package com.alex.candy_shop.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_details")
@Data
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToMany(mappedBy = "orderDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void calculateSum() {
        double sum = 0;

        /*
        for (OrderItem item : orderItems) {
            sum += item.getProduct().getPrice() * item.getQuantity();
        }
        */

        sum = orderItems.stream()
                .map(orderItem -> orderItem.getProduct().getPrice() * orderItem.getQuantity())
                .reduce((acc, x) -> acc + x)
                .get();

        order.setSum(sum);
    }
}
