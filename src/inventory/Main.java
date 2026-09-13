package inventory;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Inventory inventory = new Inventory();
        inventory.setProducts(
                FileManager.loadProducts("products.csv")
        );

        boolean running = true;

        while (running) {

            System.out.println("\n===== INVENTORY & SALES SYSTEM =====");
            System.out.println("1. View Products");
            System.out.println("2. Add Product");
            System.out.println("3. Search Product");
            System.out.println("4. Update Stock");
            System.out.println("5. Record Sale");
            System.out.println("6. View Sales Analytics");
            System.out.println("7. View Low Stock");
            System.out.println("8. Save and Exit");

            System.out.print("Choose an option: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    inventory.displayProducts();
                    break;

                case 2:
                    System.out.print("Product ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Product name: ");
                    String name = scanner.nextLine();

                    System.out.print("Category: ");
                    String category = scanner.nextLine();

                    System.out.print("Price: ");
                    double price = scanner.nextDouble();

                    System.out.print("Quantity: ");
                    int quantity = scanner.nextInt();

                    Product newProduct = new Product(id, name, category, price, quantity);

                    if (inventory.addProduct(newProduct)) {
                        System.out.println("Product added.");
                    } else {
                        System.out.println("A product with that ID already exists.");
                    }

                    break;

                case 3:
                    System.out.print("Enter product ID: ");
                    int searchId = scanner.nextInt();

                    Product product = inventory.findProduct(searchId);

                    if (product == null) {
                        System.out.println("Product not found.");
                    } else {
                        System.out.println(
                                product.getName() +
                                        " | $" + product.getPrice() +
                                        " | Quantity: " + product.getQuantity()
                        );
                    }
                    break;

                case 4:
                    System.out.print("Product ID: ");
                    int updateId = scanner.nextInt();

                    System.out.print("New quantity: ");
                    int newQuantity = scanner.nextInt();

                    if (inventory.updateQuantity(updateId, newQuantity)) {
                        System.out.println("Stock updated.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 5:
                    System.out.print("Product ID: ");
                    int saleId = scanner.nextInt();

                    System.out.print("Quantity sold: ");
                    int sold = scanner.nextInt();

                    if (inventory.recordSale(saleId, sold)) {
                        System.out.println("Sale recorded.");
                    } else {
                        System.out.println("Unable to record sale.");
                    }
                    break;

                case 6:
                    double revenue =
                            SalesAnalyzer.calculateTotalRevenue(
                                    inventory.getSales()
                            );

                    int units =
                            SalesAnalyzer.calculateTotalUnitsSold(
                                    inventory.getSales()
                            );

                    Product best =
                            SalesAnalyzer.getBestSellingProduct(
                                    inventory.getSales(),
                                    inventory.getProducts()
                            );

                    System.out.println("\n===== SALES ANALYTICS =====");
                    System.out.println("Total Revenue: $" + revenue);
                    System.out.println("Units Sold: " + units);

                    if (best != null) {
                        System.out.println(
                                "Best Selling Product: " +
                                        best.getName()
                        );
                    }

                    break;

                case 7:
                    System.out.print("Low-stock threshold: ");
                    int threshold = scanner.nextInt();

                    inventory.displayLowStockProducts(threshold);
                    break;

                case 8:
                    FileManager.saveProducts(
                            inventory.getProducts(),
                            "products.csv"
                    );

                    System.out.println("Data saved.");
                    System.out.println("Goodbye!");

                    running = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }
}