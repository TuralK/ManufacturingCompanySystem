package org.example;

import java.util.List;
import java.util.Map;

public class CSVDataLoader {
    private final String componentsFile;
    private final String productsFile;

    public CSVDataLoader(String componentsFile, String productsFile) {
        this.componentsFile = componentsFile;
        this.productsFile   = productsFile;
    }

    public List<BasicComponent> loadComponents() {
        return CSVLoader.loadComponents(componentsFile);
    }

    public List<Product> loadProducts(Map<String, Component> lookup) {
        return CSVLoader.loadProducts(productsFile, lookup);
    }
}
