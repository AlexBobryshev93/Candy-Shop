package com.alex.candy_shop.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {
    @Test(timeout = 500)
    public void moneyToDisplay() {
        assertEquals("1.00", Utils.moneyToDisplay(1));
        assertEquals("0.10", Utils.moneyToDisplay(0.1));
        assertEquals("1.11", Utils.moneyToDisplay(1.111));
    }
}