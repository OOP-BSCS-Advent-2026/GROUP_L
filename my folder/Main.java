public class Main {

    public static void main(String[] args) {

        // One array holds all three subclass types, because each one is an Item
        Item[] items = {
                new PercentDiscountItem("Photocopy (page)", 200.00, 50, 5),   // 5% off at 50+
                new NoDiscountItem("Printing (page)", 500.00),                // never discounted
                new FlatDiscountItem("Binding", 3000.00, 5, 500.00),          // UGX 500 off at 5+
                new PercentDiscountItem("Lamination", 1000.00, 10, 10)       // 10% off at 10+
        };

        // Quantities the customer is buying
        int[] quantities = {
                49, // Photocopy (page)
                2,  // Printing (page)
                4,  // Binding
                10  // Lamination
        };

        // Display the price list
        System.out.println("==== QUICKPRINT SHOP ====");
        System.out.println("---- PRICE LIST ----");
        for (int i = 0; i < items.length; i++) {
            System.out.printf(Locale.US, "%d. %-20s UGX %,.2f%n",
                    i + 1, items[i].getName(), items[i].getPrice());
        }
        System.out.println();

        // Print the itemised receipt and add up the grand total
        System.out.println("---- RECEIPT ----");
        double grandTotal = 0.0;
        for (int i = 0; i < items.length; i++) {
            // Each object runs its own version of calculateTotal()
            double lineTotal = items[i].calculateTotal(quantities[i]);

            // A discount was applied if the total is lower than price x quantity
            boolean discounted = lineTotal < items[i].getPrice() * quantities[i];
            String discountLabel = discounted ? "Discount applied" : "No discount";

            System.out.printf(Locale.US, "%d. %-20s Qty: %-4d Subtotal: UGX %,10.2f  (%s)%n",
                    i + 1, items[i].getName(), quantities[i], lineTotal, discountLabel);
            grandTotal += lineTotal;
        }
        System.out.println("============================================================");
        System.out.printf(Locale.US, "GRAND TOTAL: UGX %,.2f%n", grandTotal);
        System.out.println("============================================================");
    }
