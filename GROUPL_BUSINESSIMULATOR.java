import java.util.Locale;
/**
 * GROUP L - QuickPrint Shop
 * SENDIKKADIIWA EMILLY S25B23/050
 * KEITH ROWLAND NTEGE S25B23/029
 * EMMANUEL AINOMUGISHA S25B23/082
 * MARIE LOUIS S25B23/060
 * SHERINA AMONG M25B23/045
 * 
 */
public class GROUPL_BUSINESSIMULATOR {

    public static void main(String[] args) {

        // Step 1: Store the four items and their prices using arrays
        String[] itemNames = {"Photocopy (page)", "Printing (page)", "Binding", "Lamination"};
        double[] itemPrices = {200.00, 500.00, 3000.00, 1000.00};

        // Step 2: Display the price list (looped over the arrays)
        System.out.println("==== QUICKPRINT SHOP ====");
        System.out.println("---- PRICE LIST ----");
        for (int i = 0; i < itemNames.length; i++) {
            System.out.printf("%d. %-20s UGX %,.2f%n", i + 1, itemNames[i], itemPrices[i]);
        }
        System.out.println();

        // Step 3: Quantities the customer is buying (set in code for now)
        // Using the "Check yourself" numbers from the brief so the totals can be verified.
        int[] quantities = {
                49, // Photocopy (page)
                2,  // Printing (page)
                4,  // Binding
                10  // Lamination
        };

        // Step 4: Work out each item's subtotal (with discount rules applied)
        double[] subtotals = new double[itemNames.length];
        boolean[] discountApplied = new boolean[itemNames.length];

        for (int i = 0; i < itemNames.length; i++) {
            subtotals[i] = calculateSubtotal(i, itemPrices[i], quantities[i]);
            discountApplied[i] = isDiscountApplied(i, quantities[i]);
        }

        // Step 5: Add up all subtotals into one grand total
        double grandTotal = 0.0;
        for (double subtotal : subtotals) {
            grandTotal += subtotal;
        }

        //Step 6: Print the itemised receipt
        printReceipt(itemNames, quantities, subtotals, discountApplied, grandTotal);
    }

    /**
     * Calculates the subtotal for one item, applying that item's discount
     * rule if the quantity qualifies for it.
     *
     * Discount rules (see Group L appendix):
     * 0 = Photocopy : 50+ pages -> 5% off the photocopy total
     * 1 = Printing  : never discounted
     * 2 = Binding   : 5+ items -> flat UGX 500 off the binding total
     * 3 = Lamination: 10+ items -> 10% off the lamination total
     */
    static double calculateSubtotal(int itemIndex, double price, int quantity) {
        double subtotal = price * quantity;

        if (itemIndex == 0) { // Photocopy
            if (quantity >= 50) {
                subtotal = subtotal * 0.95;
            }
        } else if (itemIndex == 1) { // Printing - no deal, ever
            // no discount logic needed
        } else if (itemIndex == 2) { // Binding
            if (quantity >= 5) {
                subtotal = subtotal - 500.00;
            }
        } else if (itemIndex == 3) { // Lamination
            if (quantity >= 10) {
                subtotal = subtotal * 0.90;
            }
        }

        return subtotal;
    }

    /**
     * Reports whether the discount rule for this item was actually triggered,
     * so the receipt can flag it clearly.
     */
    static boolean isDiscountApplied(int itemIndex, int quantity) {
        if (itemIndex == 0) {
            return quantity >= 50;
        } else if (itemIndex == 1) {
            return false;
        } else if (itemIndex == 2) {
            return quantity >= 5;
        } else if (itemIndex == 3) {
            return quantity >= 10;
        }
        return false;
    }

    /**
     * Prints one itemised receipt line per item (quantity, subtotal, whether
     * a discount was applied), followed by the grand total.
     */
    static void printReceipt(String[] itemNames, int[] quantities, double[] subtotals,
                              boolean[] discountApplied, double grandTotal) {
        System.out.println("---- RECEIPT ----");
        for (int i = 0; i < itemNames.length; i++) {
            String discountLabel = discountApplied[i] ? "Discount applied" : "No discount";
            System.out.printf(Locale.US, "%d. %-20s Qty: %-4d Subtotal: UGX %,10.2f  (%s)%n",
                    i + 1, itemNames[i], quantities[i], subtotals[i], discountLabel);
        }
        System.out.println("============================================================");
        System.out.printf(Locale.US, "GRAND TOTAL: UGX %,.2f%n", grandTotal);
        System.out.println("============================================================");
    }
}