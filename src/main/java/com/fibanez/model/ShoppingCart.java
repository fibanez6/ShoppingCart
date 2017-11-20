package com.fibanez.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represent a shopping cart which wraps a list of items in the basket.
 *
 * ShoppingCart is a map of String -> Long where:
 *   key(String) -> Item name
 *   value(Long) -> Quantity
 *
 * @author fibanez
 */
public class ShoppingCart {

    private Map<String, Long> itemCount = new HashMap<>();

    public void addItems(List<String> items) {
        items.forEach( i -> addItem(i) );
    }

    public void addItem(String item) {
        Long count = itemCount.getOrDefault(item, 0L);
        itemCount.put(item, count + 1L);
    }

    public List<String> getItems() {
        return itemCount.keySet().stream().collect(Collectors.toList());
    }

    public long getQuantity(String item) {
        return itemCount.getOrDefault(item, 0L);
    }
}
