package inventory;

import java.util.ArrayList;

public class SalesAnalyzer {

    public static double calculateTotalRevenue(ArrayList<Sale> sales) {
        double total = 0;

        for (Sale sale : sales) {
            total += sale.getTotalPrice();
        }

        return total;
    }

    public static int calculateTotalUnitsSold(ArrayList<Sale> sales) {
        int total = 0;

        for (Sale sale : sales) {
            total += sale.getQuantitySold();
        }

        return total;
    }

    public static Product getBestSellingProduct(
            ArrayList<Sale> sales,
            ArrayList<Product> products
    ) {
        Product bestProduct = null;
        int highestSold = 0;

        for (Product product : products) {
            int totalSold = 0;

            for (Sale sale : sales) {
                if (sale.getProductId() == product.getId()) {
                    totalSold += sale.getQuantitySold();
                }
            }

            if (totalSold > highestSold) {
                highestSold = totalSold;
                bestProduct = product;
            }
        }

        return bestProduct;
    }
}