package com.fibanez.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 *
 * Represent a discount line in the bill
 *
 * @author fibanez
 */
@Data
@Setter(AccessLevel.NONE)
public class BillDiscount {

    private final String name;

    private final Double discountPercent;

    private final Double discountValue;
}
