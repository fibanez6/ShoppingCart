package com.fibanez.handler.offer;

import com.fibanez.model.*;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;

/**
 * @author fibanez
 */
public class SimpleDiscountOfferHandlerTest {

    private SimpleDiscountOfferHandler handler;
    private PriceList priceList;
    private OfferItem offerItem;

    @Before
    public void setUp() throws Exception {
        this.priceList = Mockito.mock(PriceList.class);
        this.offerItem = Mockito.mock(OfferItem.class);
        this.handler = new SimpleDiscountOfferHandler(offerItem,priceList);
    }

    @After
    public void tearDown() throws Exception {
        this.handler = null;
    }

    @Test
    public void testCreateSimpleDiscountOfferHandler() throws Exception {
        assertNotNull(handler);
    }

    @Test
    public void testHasNextReturnFalse() throws Exception {
        assertFalse(handler.hasNext());
    }

    @Test
    public void testHasNextReturnTrue() throws Exception {
        SimpleDiscountOfferHandler handlerNext= new SimpleDiscountOfferHandler(offerItem,priceList);
        handler.setNextHandler(handlerNext);
        assertTrue(handler.hasNext());
    }

    @Test
    public void testApplyOfferAndGenerateOneDiscount() throws Exception {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem("item");
        List<BillDiscount> billDiscountsResult = new ArrayList();
        OfferItem offerItem = new OfferItem("item", 1, new DiscountItem("item", 10.0));

        handler = Mockito.spy(new SimpleDiscountOfferHandler(offerItem,new PriceList(Collections.EMPTY_MAP)));
        Mockito.when(handler.proccessDiscount(anyLong())).thenReturn(new BillDiscount("item", 1.0, 10.0));
        handler.applyOffer(shoppingCart, billDiscountsResult);

        Mockito.verify(handler).hasNext();
        assertThat(billDiscountsResult, IsCollectionWithSize.hasSize(1));
    }


}
