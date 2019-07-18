package com.alex.candy_shop.entities;

import org.junit.*;

import static org.junit.Assert.*;

public class OrderTest {
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
    public void testGetSumForDisplay() {
        order.getOrderDetails()
                .getOrderItems()
                .add(new OrderItem(order.getOrderDetails(), new Product("product1", 1.1, 10), 1));

        order.getOrderDetails()
                .getOrderItems()
                .add(new OrderItem(order.getOrderDetails(), new Product("product2", 1.1, 10), 1));

        order.getOrderDetails().calculateSum();
        assertEquals("2.20", order.getSumForDisplay());
    }
}