package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CSVLoader provides static methods to load components and products from CSV files.
 */
public class CSVLoader {

    // Loads components from a CSV file located in the resources folder.
    public static List<BasicComponent> loadComponents(String filename) {
        List<BasicComponent> components = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + filename))) {
            String line;
            boolean header = true;
            while ((line = br.readLine()) != null) {
                if (header) {
                    header = false;
                    continue;
                }
                // Split using semicolon delimiter.
                String[] tokens = line.split(";");
                if (tokens.length < 5) continue;

                String name = tokens[0].trim();
                double unitCost = parseDouble(tokens[1].trim());
                double unitWeight = parseDouble(tokens[2].trim());
                String type = tokens[3].trim();
                // Remove non numeric characters from stock quantity string.
                double stockQuantity = parseDouble(tokens[4].replaceAll("[^0-9,\\.]", "").trim());

                components.add(new BasicComponent(name, unitCost, unitWeight, type, stockQuantity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return components;
    }

    // Loads products from a CSV file located in the resources folder.
    public static List<Product> loadProducts(String filename, Map<String, Component> componentLookup) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + filename))) {
            String line;
            String headerLine = br.readLine();
            if (headerLine == null) return products;
            // Split header to get component names.
            String[] headers = headerLine.split(";");
            // The first column is product name, the last is quantity.
            List<String> componentNames = new ArrayList<>();
            for (int i = 1; i < headers.length - 1; i++) {
                componentNames.add(headers[i].trim());
            }

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(";");
                if (tokens.length < 2) continue;
                String productName = tokens[0].trim();
                Map<Component, Double> requirements = new HashMap<>();
                // For each component column, parse the required quantity.
                for (int i = 1; i < tokens.length - 1; i++) {
                    String value = tokens[i].trim();
                    // Some CSV values might use a comma as the decimal separator.
                    double reqQuantity = parseDouble(value);
                    // Only add if the required quantity is greater than zero.
                    if (reqQuantity > 0) {
                        String compName = componentNames.get(i - 1);
                        Component comp = componentLookup.get(compName);
                        requirements.put(comp, reqQuantity);
                    }
                }
                // Last column is the manufacturing quantity.
                int quantity = (int) parseDouble(tokens[tokens.length - 1].trim());
                products.add(new Product(productName, requirements, quantity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Helper method to parse a String into a double.
    // Replaces a comma with a dot to handle European decimal notation.
    private static double parseDouble(String value) {
        if (value == null || value.isEmpty()) return 0.0;
        // Replace comma with dot.
        value = value.replace(",", ".");
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
