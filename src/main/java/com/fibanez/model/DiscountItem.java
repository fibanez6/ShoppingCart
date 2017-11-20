package com.fibanez.model;

import lombok.Data;

/**
 * Represents a discount apply to an item.
 *
 * @author fibanez
 */
@Data
public class DiscountItem {

    private final String name;

    private final Double discountPercent;

}
