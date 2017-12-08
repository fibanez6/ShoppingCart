package com.fibanez.handler.offer;

import com.fibanez.model.OfferItem;
import com.fibanez.model.PriceList;

/**
 * Factory which returns a offer handler.
 *
 * @author fibanez
 */
public final class OfferHandlerFactory {

    private OfferHandlerFactory() {}

    public static OfferHandler createOfferHandler(OfferItem offerItem, PriceList priceList) {
        return new SimpleDiscountOfferHandler(offerItem, priceList);
    }

}
