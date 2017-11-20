package com.fibanez.util;

import com.fibanez.model.OfferItem;
import com.google.gson.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Parse the data to:
 *    price list.  name -> price
 *    offers.
 *
 * @author fibanez
 */
@Data
@Getter(AccessLevel.NONE)
@Setter(AccessLevel.NONE)
public class ConfigParserUtil implements ConfigUtil {

    private static final String PRICE_ITEM = "priceItem";
    private static final String PRICE_ITEM_NAME = "name";
    private static final String PRICE_ITEM_PRICE = "price";
    private static final String OFFER_ITEM = "offerItem";

    private final String data;

    private final JsonElement root;

    private Gson gson = new Gson();

    public ConfigParserUtil(String data) {
        this.data = data;
        if (data == null || data.trim().length() == 0) {
            throw new IllegalArgumentException("CONFIG DATA IS NOT VALID");
        }
        JsonParser jsonParser = new JsonParser();
        this.root = jsonParser.parse(data);
    }

    @Override
    public Map<String, Double> getPrices() {
        Map<String, Double> priceList = new HashMap<>();

        JsonArray priceItems = getJsonArray(PRICE_ITEM);

        priceItems.forEach( pi -> priceList.put(
                ((JsonObject)pi).get(PRICE_ITEM_NAME).getAsString(),
                ((JsonObject)pi).get(PRICE_ITEM_PRICE).getAsDouble())
        );
        return priceList;
    }

    @Override
    public List<OfferItem> getOffers() {
        List<OfferItem> offerItems = new ArrayList<>();

        JsonArray discounts = getJsonArray(OFFER_ITEM);

        OfferItem offerItem;
        for (int i = 0; i < discounts.size() ; i++) {
            offerItem = gson.fromJson(discounts.get(i).toString(), OfferItem.class);
            offerItems.add(offerItem);
        }

        return offerItems;
    }

    private JsonArray getJsonArray(String name) {
        JsonElement jsonElement = root.getAsJsonObject().get(name);
        if (jsonElement == null) {
            return new JsonArray();
        }
        return jsonElement.getAsJsonArray();
    }

}
