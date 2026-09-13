package inventory;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Product> products;
    private ArrayList<Sale> sales;

    public Inventory() {
        products = new ArrayList<>();
        sales = new ArrayList<>();
    }

    public boolean recordSale(int productId, int quantitySold) {
        Product product = findProduct(productId);

        if (product == null) {
            return false;
        }

        if (quantitySold <= 0 || quantitySold > product.getQuantity()) {
            return false;
        }

        double totalPrice = product.getPrice() * quantitySold;

        product.setQuantity(product.getQuantity() - quantitySold);

        Sale sale = new Sale(productId, quantitySold, totalPrice);
        sales.add(sale);

        return true;
    }

    public boolean addProduct(Product product) {
        if (findProduct(product.getId()) != null) {
            return false;
        }

        products.add(product);
        return true;
    }

    public Product findProduct(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public boolean updateQuantity(int id, int newQuantity) {
        Product product = findProduct(id);

        if (product == null) {
            return false;
        }

        product.setQuantity(newQuantity);
        return true;
    }

    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        for (Product product : products) {
            System.out.println(
                    "ID: " + product.getId() +
                            " | Name: " + product.getName() +
                            " | Category: " + product.getCategory() +
                            " | Price: $" + product.getPrice() +
                            " | Quantity: " + product.getQuantity()
            );
        }
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Sale> getSales() {
        return sales;
    }

    public void displayLowStockProducts(int threshold) {
        System.out.println("\nLOW STOCK PRODUCTS");

        for (Product product : products) {
            if (product.getQuantity() <= threshold) {
                System.out.println(
                        product.getName() +
                                " | Remaining: " +
                                product.getQuantity()
                );
            }
        }
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}