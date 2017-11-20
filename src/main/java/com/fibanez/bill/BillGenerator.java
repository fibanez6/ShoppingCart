package com.fibanez.bill;

import com.fibanez.handler.offer.OfferHandlerProcessor;
import com.fibanez.model.Bill;
import com.fibanez.model.BillDiscount;
import com.fibanez.model.PriceList;
import com.fibanez.model.ShoppingCart;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

/**
 *
 * Given a price list and the offer chain, generates the bill for a shopping cart.
 *
 * @author fibanez
 */
@Data
@Getter(AccessLevel.NONE)
@Setter(AccessLevel.NONE)
public class BillGenerator {

    private final PriceList priceList;

    private final OfferHandlerProcessor processor;

    public Bill generateBill(ShoppingCart shoppingCart) {
        Double subTotal = calculateSubTotal(shoppingCart);
        List<BillDiscount> billDiscounts = processor.applyOffer(shoppingCart);
        Optional<Double> discount = totalDiscount(billDiscounts);
        Double total = subTotal - discount.orElse(0.0);
        return new Bill(subTotal, total, billDiscounts);
    }

    public Double calculateSubTotal(ShoppingCart shoppingCart) {
        return shoppingCart.getItems().stream()
                .mapToDouble(item -> priceList.getPrice(item) *  shoppingCart.getQuantity(item))
                .sum();
    }

    public Optional<Double> totalDiscount(List<BillDiscount> discounts) {
        return discounts.stream()
                .map(BillDiscount::getDiscountValue)
                .reduce((d1, d2) -> d1 + d2);
    }

}
