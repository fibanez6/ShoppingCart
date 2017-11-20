package com.fibanez.util;

import com.fibanez.model.DiscountItem;
import com.fibanez.model.OfferItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by fibanez on 18/11/17.
 */
public class ConfigParserUtilTest {

    private static String JSON_TEST_DATA = "{\n" +
            "  \"priceItem\": [\n" +
            "    {\"name\": \"Soup\", \"price\": 0.65},\n" +
            "    {\"name\": \"Bread\", \"price\": 0.80},\n" +
            "    {\"name\": \"Milk\", \"price\": 1.30},\n" +
            "    {\"name\": \"Apple\", \"price\": 1.00}\n" +
            "  ],\n" +
            "  \"offerItem\": [\n" +
            "    {\n" +
            "      \"name\": \"Soup\",\n" +
            "      \"quantity\": 2,\n" +
            "      \"discountItem\": {\n" +
            "        \"name\": \"Bread\",\n" +
            "        \"discountPercent\": 50\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Apple\",\n" +
            "      \"quantity\": 1,\n" +
            "      \"discountItem\": {\n" +
            "        \"name\": \"Apple\",\n" +
            "        \"discountPercent\": 10\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    private ConfigParserUtil configParserUtil;

    @Before
    public void setUp() throws Exception {
        this.configParserUtil = new ConfigParserUtil(JSON_TEST_DATA);
    }

    @After
    public void tearDown() throws Exception {
        this.configParserUtil = null;
    }

    @Test
    public void testCreateConfigParseWithData() throws Exception {
        assertNotNull(configParserUtil);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateConfigParseWithoutData() throws Exception {
        assertNotNull(new ConfigParserUtil(null));
    }

    @Test
    public void testCreateConfigParseWithInvalidData() throws Exception {
        assertNotNull(new ConfigParserUtil("{\"novalid\":\"novalid\"}"));
    }

    @Test
    public void testGetPricesWithInvalidData() throws Exception {
        configParserUtil = new ConfigParserUtil("{\"novalid\":\"novalid\"}");

        Map<String, Double> priceList = configParserUtil.getPrices();
        assertThat(priceList.size(), is(0));
    }

    @Test
    public void testGetPricesHasCorrectSize() throws Exception {
        Map<String, Double> priceList = configParserUtil.getPrices();
        assertThat(priceList.size(), is(4));
    }

    @Test
    public void testGetPriceListReturnPrices() throws Exception {
        Map<String, Double> priceList = configParserUtil.getPrices();

        assertThat(priceList.get("Soup"), is(0.65));
        assertThat(priceList.get("Bread"), is(0.80));
        assertThat(priceList.get("Milk"), is(1.30));
        assertThat(priceList.get("Apple"), is(1.00));
    }

    @Test
    public void testGetOffersWithInvalidData() throws Exception {
        configParserUtil = new ConfigParserUtil("{\"novalid\":\"novalid\"}");

        Map<String, Double> priceList = configParserUtil.getPrices();
        assertThat(priceList.size(), is(0));
    }

    @Test
    public void testGetOffersReturnNotEmpty() throws Exception {
        List<OfferItem> offers = configParserUtil.getOffers();
        assertThat(offers, not(hasSize(0)));
    }

    @Test
    public void testGetOffersHasCorrectSize() throws Exception {
        List<OfferItem> offers = configParserUtil.getOffers();
        assertThat(offers, hasSize(2));
    }

    @Test
    public void testGetOffersReturnDiscount() throws Exception {
        List<OfferItem> offers = configParserUtil.getOffers();

        DiscountItem discountSoup = new DiscountItem("Bread", 50.00);
        OfferItem offerSoup = new OfferItem("Soup",2,discountSoup);

        DiscountItem discountApple = new DiscountItem("Apple", 10.00);
        OfferItem offerApple = new OfferItem("Apple",1,discountApple);

        assertThat(offers, hasItems(offerSoup,offerApple));
    }

}
