package com.fibanez.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.List;

/**
 *
 * Represent a bill object.
 *
 * @author fibanez
 */
@Data
@Setter(AccessLevel.NONE)
public class Bill {

    private final Double subtotal;

    private final Double total;

    private final List<BillDiscount> billDiscounts;

}
