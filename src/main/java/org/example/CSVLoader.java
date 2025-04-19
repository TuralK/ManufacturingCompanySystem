package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for loading {@link BasicComponent} and {@link Product} objects from CSV files.
 * <p>
 * This class provides static methods that parse CSV files located in
 * the resources folder and convert the contents into corresponding object instances.
 * </p>
 */
public class CSVLoader {

    /**
     * Loads a list of {@link BasicComponent} objects from a CSV file.
     * <p>
     * Each line in the file (after the header) should be in the format:
     * <pre>
     * Component;Unit Cost (TL);Unit Weight (kg);Type;Stock Quantity
     * </pre>
     * </p>
     * The method supports numeric formats using either comma or dot as the decimal separator.
     *
     * @param filename the name of the CSV file (relative to the resources folder)
     * @return a list of loaded BasicComponent instances
     */
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

    /**
     * Loads a list of {@link Product} objects from a CSV file.
     * <p>
     * The first line of the file is a header, which contains "Product Name", 
     * followed by the names of all components, with the last column representing the "Quantity".
     * Each line in the file (after the header) should be in the format:
     * <pre>
     * Product Name;Component 1 Amount;Component 2 Amount;...;Quantity to Manufacture
     * </pre>
     * </p>
     * <p>
     * Only components with quantities greater than zero are included in the product's requirements. 
     * Components with zero or negative quantities are ignored.
     * </p>
     *
     * @param filename the name of the CSV file (relative to the resources folder)
     * @param componentLookup a map used to look up {@link Component} instances by their name (as in the CSV header)
     * @return a list of {@link Product} instances loaded from the file
     */
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
                        Component comp = componentLookup.get(compName); // Use the lookup table parameter to get Component from name
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

    /**
     * Helper method to parse a string to a {@code double}, 
     * and handles both dot and comma as decimal separators.
     *
     * @param value the string to parse
     * @return the parsed double, or 0.0 if parsing fails
     */
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
