package com.alex.candy_shop.web;

import com.alex.candy_shop.entities.Order;
import com.alex.candy_shop.entities.OrderItem;
import com.alex.candy_shop.entities.Product;
import com.alex.candy_shop.repos.OrderRepo;
import com.alex.candy_shop.repos.ProductRepo;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ShopControllerTest {
    private ShopController shopController;
    private static Order order;

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private OrderRepo orderRepo;

    @BeforeClass
    public static void orderCreation() {
        order = new Order();
        order.getOrderDetails()
                .getOrderItems()
                .add(new OrderItem(order.getOrderDetails(), new Product("product1", 1.1, 10), 1));

        order.getOrderDetails()
                .getOrderItems()
                .add(new OrderItem(order.getOrderDetails(), new Product("product2", 1.1, 10), 1));

    }

    @AfterClass
    public static void shutdown() {
        order = null;
    }

    @Before
    public void setUp() throws Exception {
        shopController = new ShopController(productRepo, orderRepo);
    }

    @After
    public void tearDown() throws Exception {
        shopController = null;
    }

    @Test
    @Ignore("testShowCart() was ignored because of following testing in testPurchase()")
    public void testShowCart() {
        shopController.showCart(order);
    }

    @Test
    public void testPurchase() {
        shopController.showCart(order);
        shopController.purchase();
        assertEquals(9, order.getOrderDetails().getOrderItems().get(0).getProduct().getInStock());
        assertEquals(9, order.getOrderDetails().getOrderItems().get(1).getProduct().getInStock());
    }

}