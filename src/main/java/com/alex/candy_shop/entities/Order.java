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

    @ManyToOne(fetch = FetchType.LAZY)
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

    public String getSumForDisplay() {
        String s = String.valueOf(Math.round(sum * 100) / 100d);
        int digitsBeforePoint = 0;
        for (int i = 0; ; i++) {
            if (s.charAt(i) == '.') break;
            digitsBeforePoint++;
        }
        while ((s.length() - digitsBeforePoint - 1 ) < 2) s = s + "0";
        return s;
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
