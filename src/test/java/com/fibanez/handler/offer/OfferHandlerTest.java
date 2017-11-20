package com.fibanez.handler.offer;

import com.fibanez.model.BillDiscount;
import com.fibanez.model.OfferItem;
import com.fibanez.model.PriceList;
import com.fibanez.model.ShoppingCart;
import static org.hamcrest.collection.IsCollectionWithSize.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author fibanez
 */
public class OfferHandlerTest {

    static class TestOfferHandler extends OfferHandler {
        @Override
        public void applyOffer(ShoppingCart cart, List<BillDiscount> discountResult) {
            discountResult.add(new BillDiscount("name", 10.0, 1.0));
            if (next != null) {
                next.applyOffer(cart, discountResult);
            }
        }
    }

    private OfferHandlerProcessor processor;

    @Before
    public void setUp() throws Exception {
        PriceList priceList = Mockito.mock(PriceList.class);
        List<OfferItem> offerItem = Collections.emptyList();
        this.processor = new OfferHandlerProcessor(offerItem,priceList);
    }

    @After
    public void tearDown() throws Exception {
        this.processor = null;
    }

    @Test
    public void testCreateProcessor() throws Exception {
        assertNotNull(processor);
    }

    @Test
    public void testAddHandlerAndApplyOffer() throws Exception {
        processor.addHandler(new TestOfferHandler());
        processor.addHandler(new TestOfferHandler());
        processor.addHandler(new TestOfferHandler());
        List<BillDiscount> result = processor.applyOffer(Mockito.mock(ShoppingCart.class));
        assertThat(result, hasSize(3));
    }

    @Test
    public void testApplyOfferWithoutHandlers() throws Exception {
        List<BillDiscount> result = processor.applyOffer(Mockito.mock(ShoppingCart.class));
        assertThat(result, hasSize(0));
    }

}
