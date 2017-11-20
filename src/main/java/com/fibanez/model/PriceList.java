package com.fibanez.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 *
 * Represent all item prices in a list which is a map of String -> Double where:
 *   key(String) -> Item name
 *   value(Double) -> Price
 *
 * @author fibanez
 */
@Data
public class PriceList {

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private final Map<String, Double> itemPrice;

    public Double getPrice(String name) {
        return itemPrice.getOrDefault(name, 0.0);
    }
}
