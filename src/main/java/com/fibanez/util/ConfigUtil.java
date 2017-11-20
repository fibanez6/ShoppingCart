package com.fibanez.util;

import com.fibanez.model.OfferItem;

import java.util.List;
import java.util.Map;

/**
 *
 * Interface for configurations
 *
 * @author fibanez
 */
public interface ConfigUtil {

    Map<String, Double> getPrices();

    List<OfferItem> getOffers();

}
