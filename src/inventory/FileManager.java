package inventory;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

    public static void saveProducts(ArrayList<Product> products, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {

            for (Product product : products) {
                writer.println(
                        product.getId() + "," +
                                product.getName() + "," +
                                product.getCategory() + "," +
                                product.getPrice() + "," +
                                product.getQuantity()
                );
            }

        } catch (IOException e) {
            System.out.println("Error saving products.");
        }
    }

    public static ArrayList<Product> loadProducts(String fileName) {
        ArrayList<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String category = data[2];
                double price = Double.parseDouble(data[3]);
                int quantity = Integer.parseInt(data[4]);

                products.add(new Product(id, name, category, price, quantity));
            }

        } catch (IOException e) {
            System.out.println("No saved product file found.");
        }

        return products;
    }
}