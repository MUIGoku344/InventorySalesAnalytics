package inventory;

public class Sale {

    private int productId;
    private int quantitySold;
    private double totalPrice;

    public Sale(int productId, int quantitySold, double totalPrice) {
        this.productId = productId;
        this.quantitySold = quantitySold;
        this.totalPrice = totalPrice;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}