package com.fibanez.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author fibanez
 */
public class ShoppingCartTest {

    private ShoppingCart shoppingCart;

    @Before
    public void setUp() throws Exception {
        this.shoppingCart = new ShoppingCart();
    }

    @After
    public void tearDown() throws Exception {
        this.shoppingCart = null;
    }

    @Test
    public void testCreateShoppingCart() {
        assertNotNull(shoppingCart);
    }

    @Test
    public void testEmptyReturnsNoItems() throws Exception {
        assertThat(shoppingCart.getItems(), empty());
    }

    @Test
    public void testAddItemReturnsOneItem() throws Exception {
        shoppingCart.addItem("Apple");
        assertThat(shoppingCart.getItems(), hasSize(1));
    }

    @Test
    public void testAddUniqueItemListReturnsItemsInBasket() throws Exception {
        List<String> items = Arrays.asList("Apple", "Milk", "Bread");
        shoppingCart.addItems(items);
        assertThat(shoppingCart.getItems(), hasSize(3));
    }

    @Test
    public void testAdd3ApplesReturnsOneItem() throws Exception {
        List<String> items = Arrays.asList("Apple", "Apple", "Apple");
        shoppingCart.addItems(items);
        assertThat(shoppingCart.getItems(), hasSize(1));
    }

    @Test
    public void testAdd3ApplesReturnsQuantity3() throws Exception {
        List<String> items = Arrays.asList("Apple", "Apple", "Apple");
        shoppingCart.addItems(items);
        assertThat(shoppingCart.getQuantity("Apple"), is(3L));
    }

    @Test
    public void testIfItemNotFoundReturnsZero() throws Exception {
        List<String> items = Arrays.asList("Apple", "Apple", "Apple");
        shoppingCart.addItems(items);
        assertThat(shoppingCart.getQuantity("NoExist"), is(0L));
    }

}
