package com.fibanez;

import com.fibanez.bill.BillGenerator;
import com.fibanez.bill.BillPrinter;
import com.fibanez.handler.offer.OfferHandlerProcessor;
import com.fibanez.model.Bill;
import com.fibanez.model.PriceList;
import com.fibanez.model.ShoppingCart;
import com.fibanez.util.ConfigUtil;
import com.fibanez.util.ConfigUtilFactory;
import com.fibanez.util.CurrencyFormat;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

/**
 * @author fibanez
 */
public class Main {

    public static final String CONFIG_FILE_NAME = "setup.json";
    public static final String ENCODING = "UTF-8";

    private static final CurrencyFormat currencyFormat = new CurrencyFormat(Locale.UK);

    public static void main(String[] args) {

        ShoppingCart shoppingCart = getShoppingCart(args);
        ConfigUtil configUtil = null;

        try {
            configUtil = getConfig(CONFIG_FILE_NAME);
        } catch (Exception e) {
            System.err.println("ERROR IN READING THE CONFIG FILE");
            System.exit(-1);
        }

        BillGenerator billGenerator = getBillGenerator(configUtil);
        Bill bill = billGenerator.generateBill(shoppingCart);

        print(bill);
    }

    public static ShoppingCart getShoppingCart(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItems(Arrays.asList(args));
        return shoppingCart;
    }

    public static ConfigUtil getConfig(String resource) throws IOException {
        //Get prices and discounts from resources folder
        String data = IOUtils.toString(Main.class.getClassLoader().getResource(resource), ENCODING);
        return ConfigUtilFactory.createConfigUtil(data);
    }

    public static BillGenerator getBillGenerator(ConfigUtil configUtil) {
        PriceList priceList = new PriceList(configUtil.getPrices());
        OfferHandlerProcessor offerHandlerProcessor = new OfferHandlerProcessor(configUtil.getOffers(), priceList);
        return new BillGenerator(priceList, offerHandlerProcessor);
    }

    public static void print(Bill bill) {
        BillPrinter printer = new BillPrinter(bill, currencyFormat);
        printer.printBill();
    }
}
