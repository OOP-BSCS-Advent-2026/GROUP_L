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

        // An arrary to store the four items and their prices using arrays
        String[] itemNames = {"Photocopy (page)", "Printing (page)", "Binding", "Lamination"};
        double[] itemPrices = {200.00, 500.00, 3000.00, 1000.00};

        // Display the price list
        System.out.println("==== QUICKPRINT SHOP ====");
        System.out.println("---- PRICE LIST ----");
        for (int i = 0; i < itemNames.length; i++) {
            System.out.printf("%d. %-20s UGX %,.2f%n", i + 1, itemNames[i], itemPrices[i]);
        }
        System.out.println();

        // Quantities the customer is buying
        
        int[] quantities = {
                49, // Photocopy (page)
                2,  // Printing (page)
                4,  // Binding
                10  // Lamination
        };

        // Work out each item's subtotal with discount rules applied
        double[] subtotals = new double[itemNames.length];
        boolean[] discountApplied = new boolean[itemNames.length];
        

        for (int i = 0; i < itemNames.length; i++) {
            subtotals[i] = calculateSubtotal(i, itemPrices[i], quantities[i]);
            discountApplied[i] = isDiscountApplied(i, quantities[i]);
        }

        // Add up all subtotals into one grand total
        double grandTotal = 0.0;
        for (double subtotal : subtotals) {
            grandTotal += subtotal;
        }

        //Print the itemised receipt
        printReceipt(itemNames, quantities, subtotals, discountApplied, grandTotal);
    }

    
     /*Adds up all subtotals into one grand total */
    static double calculateSubtotal(int itemIndex, double price, int quantity) {
        double subtotal = price * quantity;

        switch (itemIndex){
            case 0: //Photocopy
                if (quantity >= 50) {
                    subtotal = subtotal * 0.95;
                }
                break;
            case 1: //printing but no discounts offered
                break;
            case 2: //Binding
                if (quantity >= 5) {
            subtotal = subtotal - 500.00;
        }
        break;
            case 3: // Lamination
                if (quantity >= 10) {
            subtotal = subtotal * 0.90;
        }
        break;
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
