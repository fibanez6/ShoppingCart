package com.fibanez.handler.offer;

import com.fibanez.model.BillDiscount;
import com.fibanez.model.OfferItem;
import com.fibanez.model.PriceList;
import com.fibanez.model.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

/**
 * Chain of Responsibility pattern
 *
 * Constructor of the offer handler chain.
 *
 * @author fibanez
 */
public class OfferHandlerProcessor {

    private OfferHandler firstHandler;

    private OfferHandler lastHandler;

    public OfferHandlerProcessor(List<OfferItem> offerItems, PriceList priceList) {
        OfferHandler offerHandler;
        for (OfferItem offerItem : offerItems) {
            offerHandler = OfferHandlerFactory.createOfferHandler(offerItem, priceList);
            addHandler(offerHandler);
        }
    }

    public void addHandler(OfferHandler offerHandler) {
        if (firstHandler == null) {
            firstHandler = offerHandler;
        } else {
            lastHandler.setNextHandler(offerHandler);
        }
        lastHandler = offerHandler;
    }

    public List<BillDiscount> applyOffer(ShoppingCart shoppingCart) {
        List<BillDiscount> discountsResults = new ArrayList<>();
        if (firstHandler != null) {
            firstHandler.applyOffer(shoppingCart, discountsResults);
        }
        return discountsResults;
    }



}
