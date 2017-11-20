package com.fibanez.util;

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * @author fibanez
 */
public class CurrencyFormatTest {

    @Test
    public void testCurrencyFormat() throws Exception {
        CurrencyFormat currencyFormat = new CurrencyFormat(Locale.UK);
        String format = currencyFormat.format(5.0);
        assertEquals(format, "£5.00");
    }

    @Test
    public void testCurrencyFormatWithFactional() throws Exception {
        CurrencyFormat currencyFormat = new CurrencyFormat(Locale.UK);
        String format = currencyFormat.format(5.10);
        assertEquals(format, "£5.10");
    }

    @Test
    public void testCurrencyFormatOnlyWithFactional() throws Exception {
        CurrencyFormat currencyFormat = new CurrencyFormat(Locale.UK);
        String format = currencyFormat.format(0.10);
        assertEquals(format, "10p");
    }

    @Test
    public void testFractionalCurrencySymbol() throws Exception {
        CurrencyFormat currencyFormat = new CurrencyFormat(Locale.UK);
        String factional = currencyFormat.getFractionalCurrencySymbol();
        assertEquals(factional, "p");
    }

}
