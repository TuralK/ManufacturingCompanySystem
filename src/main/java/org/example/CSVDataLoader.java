package org.example;

import java.util.List;
import java.util.Map;

/**
 * A service class that loads component and product data from CSV files.
 * <p>
 * This class delegates the actual loading logic to the {@link CSVLoader} class.
 * It provides methods for loading basic components and products with 
 * the provided file paths.
 * </p>
 */
public class CSVDataLoader {
    private final String componentsFile;
    private final String productsFile;

    /**
     * Constructs a new {@code CSVDataLoader} with the specified file paths
     * for component and product CSV files relative to the resources folder.
     *
     * @param componentsFile relative path to the CSV file containing component data
     * @param productsFile   relative path to the CSV file containing product data
     */
    public CSVDataLoader(String componentsFile, String productsFile) {
        this.componentsFile = componentsFile;
        this.productsFile = productsFile;
    }

    /**
     * Loads basic components from the given components CSV file.
     * <p>
     * This method delegates to {@link CSVLoader}.
     * </p>
     *
     * @return a list of {@link BasicComponent} objects loaded from the CSV file
     */
    public List<BasicComponent> loadComponents() {
        return CSVLoader.loadComponents(componentsFile);
    }

    /**
     * Loads products from the products CSV file using the given component lookup map.
     * <p>
     * This method delegates to {@link CSVLoader}.
     * The lookup map is used to resolve component references for each product.
     * </p>
     *
     * @param lookup a map of component names to {@link Component} objects used for product manufacturing
     * @return a list of {@link Product} objects loaded from the CSV file
     */
    public List<Product> loadProducts(Map<String, Component> lookup) {
        return CSVLoader.loadProducts(productsFile, lookup);
    }
}
