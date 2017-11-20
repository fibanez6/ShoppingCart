package com.fibanez.handler.offer;

import com.fibanez.model.BillDiscount;
import com.fibanez.model.ShoppingCart;

import java.util.List;

/**
 * Chain of Responsibility pattern
 *
 * Abstract class for an offer handler chain.
 *
 * @author fibanez
 */
public abstract class OfferHandler {

    protected OfferHandler next;

    public void setNextHandler(OfferHandler next) {
        this.next = next;
    }

    public boolean hasNext() {
        return this.next != null;
    }

    public abstract void applyOffer(ShoppingCart cart, List<BillDiscount> billDiscountResult);

}
