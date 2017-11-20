package com.fibanez.model;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author fibanez
 */
public class PriceListTest {

    private static final Map<String, Double> itemPrice = new HashMap<>();
    private PriceList priceList;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        itemPrice.put("Soup", 0.65);
        itemPrice.put("Bread", 0.80);
        itemPrice.put("Milk", 1.30);
        itemPrice.put("Apple", 1.00);
    }

    @Before
    public void setUp() throws Exception {
        this.priceList = new PriceList(itemPrice);
    }

    @After
    public void tearDown() throws Exception {
        this.priceList = null;
    }

    @Test
    public void testCreatePriceList() {
        assertNotNull(priceList);
    }

    @Test
    public void testGetPriceReturnPrice() throws Exception {
        assertThat(priceList.getPrice("Soup"), is(0.65));
        assertThat(priceList.getPrice("Bread"), is(0.80));
        assertThat(priceList.getPrice("Milk"), is(1.30));
        assertThat(priceList.getPrice("Apple"), is(1.00));
    }

    @Test
    public void testIfItemNotFoundReturnsZero() throws Exception {
        assertThat(priceList.getPrice("noExist"), is(0.00));
    }

}
