package com.alex.candy_shop.entities;

import org.junit.*;

import static org.junit.Assert.*;

public class ProductTest {
    private Product product;

    @BeforeClass
    public static void prepare() throws Exception {
    }

    @AfterClass
    public static void finish() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        product = new Product();
    }

    @After
    public void tearDown() throws Exception {
        product = null;
    }

    @Test(timeout = 500)
    public void testGetPriceForDisplay() {
        product.setPrice(1);
        assertEquals("1.00", product.getPriceForDisplay());
        product.setPrice(0.1);
        assertEquals("0.10", product.getPriceForDisplay());
        product.setPrice(1.111);
        assertEquals("1.11", product.getPriceForDisplay());
    }

}