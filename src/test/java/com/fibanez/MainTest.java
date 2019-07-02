package com.fibanez;

import com.fibanez.bill.BillGenerator;
import com.fibanez.model.Bill;
import com.fibanez.model.ShoppingCart;
import com.fibanez.util.ConfigUtil;
import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author fibanez
 */
public class MainTest {

    @Test
    public void testShoppingCart() throws Exception {
        ShoppingCart shoppingCart = Main.getShoppingCart(new String[]{"Apple", "Milk", "Bread"});
        assertThat(shoppingCart.getQuantity("Apple"), is(1L));
        assertThat(shoppingCart.getQuantity("Milk"), is(1L));
        assertThat(shoppingCart.getQuantity("Bread"), is(1L));
    }

    @Test
    public void testShoppingCartWithRepeatedItems() throws Exception {
        ShoppingCart shoppingCart = Main.getShoppingCart(new String[]{
                "Apple", "Apple", "Apple", "Milk", "Milk", "Bread"});
        assertThat(shoppingCart.getQuantity("Apple"), is(3L));
        assertThat(shoppingCart.getQuantity("Milk"), is(2L));
        assertThat(shoppingCart.getQuantity("Bread"), is(1L));
    }

    @Test
    public void testGetConfigReturnsConfigUtil() throws Exception {
        ConfigUtil configUtil = Main.getConfig("testSetup.json");
        assertNotNull(configUtil);
    }

    @Test
    public void testGetPriceFromConfigReturnsNotEmpty() throws Exception {
        ConfigUtil configUtil = Main.getConfig("testSetup.json");
        assertThat(configUtil.getPrices().size(), not(is(0)));
    }

    @Test
    public void testGetDiscountsFromConfigReturnsNotEmpty() throws Exception {
        ConfigUtil configUtil = Main.getConfig("testSetup.json");
        assertThat(configUtil.getOffers(), hasItems());
    }

    @Test
    public void testGetBillGeneratorReturnBillGenerator() throws Exception {
        ConfigUtil configUtil = mock(ConfigUtil.class);
        when(configUtil.getPrices()).thenReturn(Collections.EMPTY_MAP);
        when(configUtil.getOffers()).thenReturn(Collections.EMPTY_LIST);
        BillGenerator billGenerator = Main.getBillGenerator(configUtil);
        assertNotNull(billGenerator);
    }

    @Test
    public void testShopping4itemsAnd2Discounts() throws Exception {
        ShoppingCart shoppingCart = Main.getShoppingCart(new String[]{"Apple", "Milk", "Bread", "Soup", "Soup"});
        ConfigUtil configUtil = Main.getConfig("testSetup.json");
        BillGenerator billGenerator = Main.getBillGenerator(configUtil);
        Bill bill = billGenerator.generateBill(shoppingCart);

        assertThat(bill.getSubtotal(), is(4.40));
        assertThat(bill.getTotal(), closeTo(3.90, 0.000001));
        assertThat(bill.getBillDiscounts(), hasSize(2));
    }
}
