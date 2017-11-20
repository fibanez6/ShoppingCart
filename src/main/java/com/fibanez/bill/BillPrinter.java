package com.fibanez.bill;

import com.fibanez.model.Bill;
import com.fibanez.model.BillDiscount;
import com.fibanez.util.CurrencyFormat;
import lombok.Data;

/**
 * Prints a bill.
 *
 * @author fibanez
 */
@Data
public class BillPrinter {

    private static String NEW_LINE = System.getProperty("line.separator");
    public static String NO_OFFER_AVAILABLE = "(No offers available)";

    private final Bill bill;

    private final CurrencyFormat currencyFormat;

    public void printBill() {
        String bill = generateBill();
        System.out.println(bill);
    }

    public String generateBill() {
        StringBuilder out = new StringBuilder();
        appendSubtotal(out);
        bill.getBillDiscounts().forEach(d -> appendDiscount(out, d));
        if (bill.getBillDiscounts().isEmpty()) {
            appendNoOffer(out);
        }
        appendTotal(out);
        return out.toString();
    }

    private void appendSubtotal(StringBuilder out) {
        out.append("Subtotal: ").append(currencyFormat.format(bill.getSubtotal()));
        out.append(NEW_LINE);
    }

    private void appendDiscount(StringBuilder out, BillDiscount discount) {
        out.append(discount.getName())
                .append(" ")
                .append(discount.getDiscountPercent())
                .append("% off: -")
                .append(currencyFormat.format(discount.getDiscountValue()))
                .append(NEW_LINE);
    }

    private void appendNoOffer(StringBuilder out) {
        out.append(NO_OFFER_AVAILABLE);
        out.append(NEW_LINE);
    }

    private void appendTotal(StringBuilder out) {
        out.append("Total: ").append(currencyFormat.format(bill.getTotal()));
        out.append(NEW_LINE);
    }
}