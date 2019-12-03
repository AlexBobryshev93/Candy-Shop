package com.alex.candy_shop.web;

import com.alex.candy_shop.entities.Order;
import com.alex.candy_shop.entities.OrderItem;
import com.alex.candy_shop.entities.Product;
import com.alex.candy_shop.entities.User;
import com.alex.candy_shop.repos.OrderRepo;
import com.alex.candy_shop.repos.ProductRepo;
import com.alex.candy_shop.repos.UserRepo;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopControllerTest {
    private static Order order;
    private static User user;
    private ShopController shopController;

    @Mock
    private Model model;
    @Mock
    private ProductRepo productRepo;
    @Mock
    private OrderRepo orderRepo;
    @Mock
    private UserRepo userRepo;

    @BeforeClass
    public static void orderCreation() {
        user = new User();
        order = new Order();
        order.setUser(user);

        user.setUsername("test");
        user.setPassword("test");
        user.setMoneyBalance(200);

        order.getOrderDetails()
                .getOrderItems()
                .add(new OrderItem(order.getOrderDetails(),
                        new Product("product1", 1.1, 10),
                        1)
                );

        order.getOrderDetails()
                .getOrderItems()
                .add(new OrderItem(order.getOrderDetails(),
                        new Product("product2", 1.1, 10), 1)
                );
    }

    @AfterClass
    public static void shutdown() {
        order = null;
        user = null;
    }

    @Before
    public void setUp() throws Exception {
        shopController = new ShopController(productRepo, orderRepo, userRepo);
    }

    @After
    public void tearDown() throws Exception {
        shopController = null;
    }

    @Test
    @Ignore("testShowCart() was ignored because of testing in testPurchase()")
    public void testShowCart() {
        shopController.showCart(order);
    }

    @Test
    public void testPurchase() {
        shopController.showCart(order);
        shopController.purchase(order, model);
        assertEquals(9, order.getOrderDetails().getOrderItems().get(0).getProduct().getInStock());
        assertEquals(9, order.getOrderDetails().getOrderItems().get(1).getProduct().getInStock());
        assertEquals(197.8, order.getUser().getMoneyBalance(), 0.05);
    }

}