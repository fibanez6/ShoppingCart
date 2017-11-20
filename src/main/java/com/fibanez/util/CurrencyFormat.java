package com.fibanez.util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * Given a Locate, formats the price amount to locate currency
 *
 * @author fibanez
 */
public class CurrencyFormat {

    private final NumberFormat numberFormat;

    public CurrencyFormat(Locale locale) {
        this.numberFormat = NumberFormat.getCurrencyInstance(locale);
    }

    public String format(Double doubleValue) {
        if (Math.abs(doubleValue) < 1)  {
            Double d = doubleValue * 100;
            return d.longValue() + getFractionalCurrencySymbol();
        }
        return numberFormat.format(doubleValue);
    }

    public String getFractionalCurrencySymbol() {
        String result = "";
        String currencyCode = numberFormat.getCurrency().getCurrencyCode();
        switch (currencyCode) {
            case "GBP":
                result = "p";
                break;
        }
        return result;
    }


}
