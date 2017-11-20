package com.fibanez.model;

import lombok.Data;

/**
 *
 * Represents a rule offer to apply to the shopping cart
 *
 * @author fibanez
 */
@Data
public class OfferItem {

    private final String name;

    private final int quantity;

    private final DiscountItem discountItem;

}
