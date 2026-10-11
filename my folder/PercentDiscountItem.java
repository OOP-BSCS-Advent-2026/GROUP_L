//GROUP L - QuickPrint Shop
 //The two items chosen are Photocopy and Lamination.//
//This is a subclass that is inheriting fields from the superclass item//
 
public class PercentDiscountItem extends Item {

    private int threshold;    //threshold is how many items on must buy to qualify for a discount
    private double percentOff; //percentage discount

    
    public PercentDiscountItem(String name, double price, int threshold, double percentOff) {
        super(name, price); //this calls the item constructor which stores the name and price
         
        if (threshold <= 0) {
        throw new IllegalArgumentException("Threshold must be greater than zero");
    }
    if (percentOff <= 0 || percentOff > 100) {
        throw new IllegalArgumentException("Percent off must be between 0 and 100");
    }
        
        this.threshold = threshold;
        this.percentOff = percentOff;
    }

    @Override
    public double calculateTotal(int quantity) {
        double subtotal = super.calculateTotal(quantity);
        if (quantity >= threshold) {
            subtotal = subtotal * (1 - percentOff / 100.0);
        }
        return subtotal;
    }
}
