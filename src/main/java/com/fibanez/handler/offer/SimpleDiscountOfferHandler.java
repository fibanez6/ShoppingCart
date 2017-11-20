package com.fibanez.handler.offer;

import com.fibanez.model.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Chain of Responsibility pattern
 *
 * Chain which applies a discount.
 *
 * @author fibanez
 */
@Data
@Getter(AccessLevel.NONE)
@Setter(AccessLevel.NONE)
public class SimpleDiscountOfferHandler extends OfferHandler {

    private final OfferItem offerItem;

    private final PriceList priceList;

    @Override
    public void applyOffer(ShoppingCart shoppingCart, List<BillDiscount> billDiscountsResult) {
        long itemQuantity = shoppingCart.getQuantity(offerItem.getName());

        if (itemQuantity > 0 && itemQuantity >= offerItem.getQuantity()) {
            BillDiscount billDiscount = proccessDiscount(itemQuantity);
            billDiscountsResult.add(billDiscount);
        }

        if (hasNext()) {
            next.applyOffer(shoppingCart,billDiscountsResult);
        }
    }

    public BillDiscount proccessDiscount(Long itemQuantity) {
        DiscountItem discountItem = offerItem.getDiscountItem();
        Double itemPrice = priceList.getPrice(discountItem.getName());

        Double discountValue = calculateDiscount(itemQuantity, itemPrice, discountItem.getDiscountPercent());
        return new BillDiscount(discountItem.getName(), discountItem.getDiscountPercent(), discountValue);
    }

    public static Double calculateDiscount(long quantity, Double price, Double discountPercent) {
        return (quantity * price * discountPercent) / 100;
    }
}
