package com.fibanez.bill;

import com.fibanez.model.Bill;
import com.fibanez.model.BillDiscount;
import com.fibanez.util.CurrencyFormat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * Created by fibanez on 19/11/17.
 */
public class BillPrinterTest {

    private static CurrencyFormat currencyFormat = new CurrencyFormat(Locale.UK);

    private BillDiscount billDiscount;

    @Before
    public void setUp() throws Exception {
        this.billDiscount = Mockito.spy(new BillDiscount("testDiscount", 10.0, 5.0));
    }

    @After
    public void tearDown() throws Exception {
        this.billDiscount = null;
    }

    @Test
    public void testPrintBillWithDiscount() throws Exception {
        Bill bill = Mockito.spy(new Bill(10.0, 5.0, Arrays.asList(billDiscount)));
        BillPrinter printer = new BillPrinter(bill, currencyFormat);
        printer.printBill();

        Mockito.verify(bill).getSubtotal();
        Mockito.verify(bill).getTotal();

        Mockito.verify(billDiscount).getName();
        Mockito.verify(billDiscount).getDiscountPercent();
        Mockito.verify(billDiscount).getDiscountValue();
    }

    @Test
    public void testPrintBillWitouthDiscount() throws Exception {
        Bill bill = Mockito.spy(new Bill(10.0, 5.0, Collections.EMPTY_LIST));
        BillPrinter printer = Mockito.spy(new BillPrinter(bill, currencyFormat));
        printer.printBill();

        Mockito.verify(bill).getSubtotal();
        Mockito.verify(bill).getTotal();
    }

    @Test
    public void testGenerateBillWithDiscountReturnBill() throws Exception {
        Bill bill = new Bill(10.0, 5.0, Arrays.asList(billDiscount));
        BillPrinter printer = new BillPrinter(bill, currencyFormat);
        String printedBill = printer.generateBill();

        assertThat(printedBill, containsString("Subtotal: £10.00"));
        assertThat(printedBill, containsString("testDiscount 10.0% off: -£5.00"));
        assertThat(printedBill, containsString("Total: £5.00"));
        assertThat(printedBill, not(containsString(BillPrinter.NO_OFFER_AVAILABLE)));
    }

    @Test
    public void testGenerateBillWithOutDiscountReturnBillWithOffer() throws Exception {
        Bill bill = new Bill(10.0, 5.0, Collections.EMPTY_LIST);
        BillPrinter printer = new BillPrinter(bill, currencyFormat);
        String printedBill = printer.generateBill();

        assertThat(printedBill, containsString(BillPrinter.NO_OFFER_AVAILABLE));
    }

}
