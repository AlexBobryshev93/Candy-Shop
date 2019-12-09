package com.alex.candy_shop.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderDetailsTest {
    private Order order;

    @Before
    public void setUp() throws Exception {
        order = new Order();
    }

    @After
    public void tearDown() throws Exception {
        order = null;
    }

    @Test
    public void testCalculateSum() {
        order.getOrderDetails().getOrderItems()
                .add(new OrderItem(order.getOrderDetails(),
                        new Product("product1", 1.1, 10), 1)
                );

        order.getOrderDetails().getOrderItems()
                .add(new OrderItem(order.getOrderDetails(),
                        new Product("product2", 1.1, 10), 1)
                );

        order.calculateSum();

        assertEquals(2.2, order.getOrderDetails().getOrder().getSum(), 0);
    }
}