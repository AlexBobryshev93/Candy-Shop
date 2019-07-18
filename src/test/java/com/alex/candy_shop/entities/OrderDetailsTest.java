package com.alex.candy_shop.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderDetailsTest {
    private Order order;
    private OrderDetails orderDetails;

    @Before
    public void setUp() throws Exception {
        order = new Order();
        orderDetails = new OrderDetails();
        orderDetails.setOrder(order);
    }

    @After
    public void tearDown() throws Exception {
        orderDetails = null;
    }

    @Test
    public void testCalculateSum() {
        orderDetails.getOrderItems()
                .add(new OrderItem(orderDetails, new Product("product1", 1.1, 10), 1));

        orderDetails.getOrderItems()
                .add(new OrderItem(orderDetails, new Product("product2", 1.1, 10), 1));

        orderDetails.calculateSum();
        assertEquals(2.2, orderDetails.getOrder().getSum(), 0);
    }
}