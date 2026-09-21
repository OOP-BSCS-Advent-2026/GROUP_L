/**
 * GROUP L - QuickPrint Shop
 * An item that gives a fixed (flat) discount once the customer
 * buys a minimum quantity. Extends Item to reuse name and price.
 */
public class FlatDiscountItem extends Item {
    // Minimum quantity needed to qualify for the discount

    private int threshold;
    // Fixed amount (UGX) deducted from the total when the discount applies
    private double flatAmount;

    public FlatDiscountItem(String name, double price,
                            int threshold, double flatAmount) {
        super(name, price);// Set name and price in the superclass
        this.threshold = threshold;
        this.flatAmount = flatAmount;
    }

    @Override
    public double calculateTotal(int quantity) {
         // Base total (price x quantity); also validates that quantity is not negative
        double subtotal = super.calculateTotal(quantity);
        // Apply the discount only if the threshold is reached
        if (quantity >= threshold) {
            // Math.max prevents the total from going below zero
            subtotal = Math.max(0, subtotal - flatAmount);
        }
        return subtotal;
    }
    
}
 